package com.example.mostpopulararticlestask.datasource


import com.example.mostpopulararticlestask.data.di.ApiService
import com.example.mostpopulararticlestask.data.mappers.ShareMappper
import com.example.mostpopulararticlestask.data.models.MostPobularRemoteData
import com.example.mostpopulararticlestask.domain.models.Article
import com.example.mostpopulararticlestask.domain.models.MostPobularData
import com.example.mostpopulararticlestask.domain.usecases.RemoteRepo
import io.reactivex.Single
import javax.inject.Inject

class FakeRepoImpl @Inject constructor() : RemoteRepo {


    override fun getMostPopular(period: String?): Single<MostPobularData> {
        val article = Article("", "section", "source", "title", "type", "abstract")
        val mostPobularData = MostPobularData("", 0, listOf(article), "")
        return Single.just(mostPobularData)
    }
}
