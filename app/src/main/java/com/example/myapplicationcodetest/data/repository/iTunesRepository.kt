package com.example.myapplicationcodetest.data.repository

import com.example.myapplicationcodetest.data.remote.ITunesService


class iTunesRepository constructor(private val iTunesService: ITunesService) {

    fun getGallery() = iTunesService.getGallery()
}