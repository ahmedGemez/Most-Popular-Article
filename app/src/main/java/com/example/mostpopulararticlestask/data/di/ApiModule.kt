package com.example.mostpopulararticlestask.data.di


import com.example.mostpopulararticlestask.data.repos.RemoteRepoImpl
import com.example.mostpopulararticlestask.domain.usecases.RemoteRepo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named


@Module(includes = [NetworkModule::class])
public class ApiModule {
    @Provides
    public fun provideRemoteRepo(remoteRepoImpl: RemoteRepoImpl): RemoteRepo {
        return remoteRepoImpl;
    }

}