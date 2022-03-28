package com.example.myapplication.repository.network

import com.example.myapplication.data.entity.BusinessResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("businesses/search")
    suspend fun searchBusiness(@Query("term")term: String, @Query("location")location: String): BusinessResponse
}
