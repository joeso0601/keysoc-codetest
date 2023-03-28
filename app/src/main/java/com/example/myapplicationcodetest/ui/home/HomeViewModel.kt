package com.example.myapplicationcodetest.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.myapplicationcodetest.data.model.GalleryListModel
import com.example.myapplicationcodetest.data.model.GalleryModel
import com.example.myapplicationcodetest.data.repository.iTunesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val repository: iTunesRepository) : ViewModel() {


    val galleryList = MutableLiveData<List<GalleryModel>>()



    fun getGallery() {
        val response = repository.getGallery()
        response.enqueue(object : Callback<GalleryListModel> {
            override fun onResponse(call: Call<GalleryListModel>, response: Response<GalleryListModel>) {
                galleryList.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<GalleryListModel>, t: Throwable) {

            }
        })
    }
}