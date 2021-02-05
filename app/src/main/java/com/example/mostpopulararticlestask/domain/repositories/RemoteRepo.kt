package com.example.mostpopulararticlestask.domain.usecases

import com.example.mostpopulararticlestask.domain.models.MostPobularData
import io.reactivex.Single

interface RemoteRepo {
    fun getMostPopular(period:String?): Single<MostPobularData>

}