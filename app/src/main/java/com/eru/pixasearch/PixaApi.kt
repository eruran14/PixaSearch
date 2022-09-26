package com.eru.pixasearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {

    @GET("api/")
    fun getImages(@Query("key")key: String = "30142579-663a74a40cfa32381d7859bb3",
                    @Query("q") keyWord: String,
                    @Query("page") page:Int = 1,
                    @Query("per_page") perPage: Int = 10): Call<PixaModel>
}