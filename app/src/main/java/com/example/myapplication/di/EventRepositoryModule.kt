package com.example.myapplication.di

import com.example.myapplication.data.source.EventDataSource
import com.example.myapplication.repository.EventsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventRepositoryModule {

    @Singleton
    @Provides
    fun provideEventRepository(
        @AppModule.RemoteEventsDataSource remoteTasksDataSource: EventDataSource,
        @AppModule.LocalEventsDataSource localTasksDataSource: EventDataSource,
        ioDispatcher: CoroutineDispatcher
    ): EventsRepository = EventsRepository(
        remoteTasksDataSource, localTasksDataSource, ioDispatcher
    )
}