package com.example.mostpopulararticlestask.domain.usecases


import com.example.mostpopulararticlestask.domain.models.MostPobularData
import io.reactivex.Single
import javax.inject.Inject

class GetShareDetailsUseCase @Inject constructor(val apiRepo:RemoteRepo) {


   fun getMostPopular(period:String?): Single<MostPobularData> {
       return  apiRepo.getMostPopular(period);
   }


}