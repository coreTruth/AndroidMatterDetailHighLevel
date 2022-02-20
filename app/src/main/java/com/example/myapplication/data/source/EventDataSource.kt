package com.example.myapplication.data.source

import com.example.myapplication.data.Event
import com.example.myapplication.repository.network.NetworkResource

interface EventDataSource {
    suspend fun getEvents(): NetworkResource<List<Event>>
    suspend fun insertEvents(events: List<Event>)
}