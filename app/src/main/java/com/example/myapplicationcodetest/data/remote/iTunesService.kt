package com.example.myapplicationcodetest.data.remote


import com.example.myapplicationcodetest.data.model.GalleryListModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ITunesService {
    @GET("search?term=jack+johnson&entity=album")
    fun getGallery(): Call<GalleryListModel>

    companion object {

        var iTunesService: ITunesService? = null

        fun getInstance(): ITunesService {

            if (iTunesService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://itunes.apple.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                iTunesService = retrofit.create(ITunesService::class.java)
            }
            return iTunesService!!
        }
    }
}