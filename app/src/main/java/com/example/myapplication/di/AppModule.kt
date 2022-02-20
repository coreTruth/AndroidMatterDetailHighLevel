package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.PhunDatabase
import com.example.myapplication.data.source.EventDataSource
import com.example.myapplication.data.source.LocalDataSource
import com.example.myapplication.data.source.RemoteDataSource
import com.example.myapplication.repository.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteEventsDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalEventsDataSource

    @Singleton
    @RemoteEventsDataSource
    @Provides
    fun provideRemoteDataSource(api: ApiInterface): EventDataSource =
        RemoteDataSource(api)

    @Singleton
    @LocalEventsDataSource
    @Provides
    fun provideLocalDataSource(
        database: PhunDatabase
    ): EventDataSource = LocalDataSource(database.eventDao())

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): PhunDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            PhunDatabase::class.java,
            "Phun.db"
        ).build()

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}

