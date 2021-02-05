package com.example.mostpopulararticlestask.data.di

import com.example.mostpopulararticlestask.data.di.APIConstants.APIKEY
import com.example.mostpopulararticlestask.data.di.APIConstants.BASEURL
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier

@Module
public class NetworkModule {

    private var okHttpClient: OkHttpClient? = null
    private var httpClient: OkHttpClient.Builder? = null

    public fun initOkHttp() {
        val builder = OkHttpClient.Builder()
        builder.hostnameVerifier(HostnameVerifier { hostname, session -> true })
        httpClient = OkHttpClient().newBuilder()

            //.sslSocketFactory(sslSocketFactory!!,xtm)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val originalHttpUrl: HttpUrl = chain.request().url
                    val url: HttpUrl = originalHttpUrl.newBuilder()
                        .addQueryParameter("api-key", APIKEY).build();
                    val requestBuilder: Request.Builder = chain.request().newBuilder()
                        .url(url)
                    val request = requestBuilder
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json").build()
                    return chain.proceed(request)
                }
            })
        val interceptor = HttpLoggingInterceptor()
        interceptor.redactHeader("Authorization")
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient!!.addInterceptor(interceptor)
        okHttpClient = httpClient!!.build()

    }

    @Singleton
    @Provides
    public fun providePostApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    public fun provideRetrofitInterface(): Retrofit {
        initOkHttp()
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }


}
