package com.example.mostpopulararticlestask.data.mappers


import com.example.mostpopulararticlestask.data.models.MostPobularRemoteData
import com.example.mostpopulararticlestask.domain.models.MostPobularData
import com.example.mostpopulararticlestask.domain.models.Article

import javax.inject.Inject


class ShareMappper  @Inject constructor() {


    fun toMostPobularData(mostPobularRemoteData: MostPobularRemoteData): MostPobularData {
        val resultList = mutableListOf<Article>()
        for (item in mostPobularRemoteData.results) {
            val result = Article(item.publishedDate,item.section,item.source,item.title,item.type,item.abstract)
            resultList.add(result)
        }
        return MostPobularData(
            mostPobularRemoteData.copyright ,
            mostPobularRemoteData.numResults,
            resultList,
            mostPobularRemoteData.status
        )
    }
}