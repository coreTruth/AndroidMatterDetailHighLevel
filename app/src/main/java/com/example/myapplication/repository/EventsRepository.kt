package com.example.myapplication.repository

import com.example.myapplication.data.Event
import com.example.myapplication.data.source.EventDataSource
import com.example.myapplication.repository.network.NetworkResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventsRepository(
    private val remoteDataSource: EventDataSource,
    private val localDataSource: EventDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getEvents(): List<Event> = withContext(ioDispatcher) {
        when(val response = remoteDataSource.getEvents()) {
            is NetworkResource.Success -> handleSuccess(response.value)
            is NetworkResource.Failure -> (localDataSource.getEvents() as NetworkResource.Success).value
        }
    }

    private suspend fun handleSuccess(events: List<Event>): List<Event> = withContext(ioDispatcher) {
        launch { localDataSource.insertEvents(events) }
        events
    }
}