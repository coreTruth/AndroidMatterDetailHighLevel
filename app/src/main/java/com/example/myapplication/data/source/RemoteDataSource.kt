package com.example.myapplication.data.source

import com.example.myapplication.data.Event
import com.example.myapplication.repository.network.ApiInterface
import com.example.myapplication.repository.network.NetworkResource
import com.example.myapplication.repository.network.SafeApiCall

class RemoteDataSource internal constructor(
    private val api: ApiInterface,
): SafeApiCall, EventDataSource{
    override suspend fun getEvents(): NetworkResource<List<Event>> =
        safeApiCall { api.getEvents() }

    override suspend fun insertEvents(events: List<Event>) = Unit
}