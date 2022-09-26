package com.eru.pixasearch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    private val retrofit = Retrofit.Builder().baseUrl("https://pixabay.com/").addConverterFactory(
        GsonConverterFactory.create()).build()

    fun getApi() = retrofit.create(PixaApi::class.java)
}