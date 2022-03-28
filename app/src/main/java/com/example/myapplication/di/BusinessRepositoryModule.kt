package com.example.myapplication.di

import com.example.myapplication.data.source.BusinessDataSource
import com.example.myapplication.di.qualifiers.LocalBusinessDataSource
import com.example.myapplication.di.qualifiers.RemoteBusinessDataSource
import com.example.myapplication.repository.BusinessRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BusinessRepositoryModule {
    @Singleton
    @Provides
    fun provideBusinessRepository(
        @RemoteBusinessDataSource remoteTasksDataSource: BusinessDataSource,
        @LocalBusinessDataSource localTasksDataSource: BusinessDataSource,
        ioDispatcher: CoroutineDispatcher
    ): BusinessRepository = BusinessRepository(
        remoteTasksDataSource, localTasksDataSource, ioDispatcher
    )
}
