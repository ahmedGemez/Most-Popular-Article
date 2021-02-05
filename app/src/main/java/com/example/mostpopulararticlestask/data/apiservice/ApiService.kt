package com.example.mostpopulararticlestask.data.di

import com.example.mostpopulararticlestask.data.models.MostPobularRemoteData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface  ApiService {
    @GET(APIConstants.GETMOSTPOBULAR)
    fun getMostPobularData(@Path("period")  period:String?): Single<MostPobularRemoteData>
}