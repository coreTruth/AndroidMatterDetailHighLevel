package com.example.myapplication.di

import com.example.myapplication.repository.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {
    private const val BASE_URL = "https://raw.githubusercontent.com/phunware-services/"

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .build()

    @Singleton
    @Provides
    fun providesEventApi(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)
}