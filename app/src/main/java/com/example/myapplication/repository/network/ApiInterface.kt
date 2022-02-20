package com.example.myapplication.repository.network

import com.example.myapplication.data.Event
import retrofit2.http.GET

interface ApiInterface {
    @GET("dev-interview-homework/master/feed.json")
    suspend fun getEvents(): List<Event>
}