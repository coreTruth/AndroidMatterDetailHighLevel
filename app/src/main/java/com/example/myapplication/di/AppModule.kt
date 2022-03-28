package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.TuroDatabase
import com.example.myapplication.data.source.BusinessDataSource
import com.example.myapplication.data.source.LocalDataSource
import com.example.myapplication.data.source.RemoteDataSource
import com.example.myapplication.di.qualifiers.LocalBusinessDataSource
import com.example.myapplication.di.qualifiers.RemoteBusinessDataSource
import com.example.myapplication.repository.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @RemoteBusinessDataSource
    @Provides
    fun provideRemoteDataSource(api: ApiInterface): BusinessDataSource =
        RemoteDataSource(api)

    @Singleton
    @LocalBusinessDataSource
    @Provides
    fun provideLocalDataSource(
        database: TuroDatabase
    ): BusinessDataSource = LocalDataSource(database.businessDao())

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): TuroDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            TuroDatabase::class.java,
            "turo.db"
        ).build()

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}

