package com.example.myapplication.data.source

import com.example.myapplication.data.Event
import com.example.myapplication.data.EventDao
import com.example.myapplication.repository.network.NetworkResource

class LocalDataSource internal constructor(
    private val eventDao: EventDao,
) : EventDataSource {
    override suspend fun getEvents(): NetworkResource<List<Event>> =
        NetworkResource.Success(eventDao.getEvents())

    override suspend fun insertEvents(events: List<Event>) =
        eventDao.deleteAndInsertAll(events)
}