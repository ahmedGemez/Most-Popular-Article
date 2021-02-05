package com.example.mostpopulararticlestask.domain.models


import com.google.gson.annotations.SerializedName

data class MostPobularData(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String
)