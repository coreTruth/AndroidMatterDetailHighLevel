package com.example.myapplication.di

import com.example.myapplication.di.qualifiers.WithAuth
import com.example.myapplication.repository.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun providesSearchApi(@WithAuth retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)
}
