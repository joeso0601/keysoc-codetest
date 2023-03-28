package com.example.myapplicationcodetest.data.model

import com.google.gson.annotations.SerializedName

data class GalleryListModel(
    @SerializedName("resultCount") val resultCount : Int,
    @SerializedName("results") val results : List<GalleryModel>
    )
