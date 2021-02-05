package com.example.mostpopulararticlestask.domain.models


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    @SerializedName("published_date")
    val publishedDate: String?,
    @SerializedName("section")
    val section: String?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("abstract")
    val `abstract`: String?
): Parcelable