package com.example.mostpopulararticlestask.data.repos


import com.example.mostpopulararticlestask.data.di.ApiService
import com.example.mostpopulararticlestask.data.mappers.ShareMappper
import com.example.mostpopulararticlestask.data.models.MostPobularRemoteData
import com.example.mostpopulararticlestask.domain.models.MostPobularData
import com.example.mostpopulararticlestask.domain.usecases.RemoteRepo
import io.reactivex.Single
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val shareMapper: dagger.Lazy<ShareMappper>) : RemoteRepo {


    override fun getMostPopular(period:String?): Single<MostPobularData> {
      return  apiService.getMostPobularData(period).map {
          shareMapper.get().toMostPobularData(it)
      }
    }


}