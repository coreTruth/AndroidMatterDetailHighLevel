package com.example.myapplication.di

import com.example.myapplication.di.CoreNetworkModule.API_TOKEN
import kotlinx.coroutines.runBlocking
import okhttp3.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
        var request = chain.request()
        request = addAuthHeader(request, API_TOKEN)
        return@runBlocking chain.proceed(request)
    }

    private fun addAuthHeader(originalRequest: Request, token: String): Request = originalRequest
        .newBuilder()
        .header(AUTHORIZATION_HEADER_KEY, "Bearer $token")
        .build()

    companion object {
        const val AUTHORIZATION_HEADER_KEY = "Authorization"
    }
}
