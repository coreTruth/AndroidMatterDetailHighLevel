package com.example.myapplication.di

import com.example.myapplication.BuildConfig
import com.example.myapplication.di.qualifiers.WithAuth
import com.example.myapplication.repository.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {
    private const val BASE_URL = "https://api.yelp.com/v3/"
    const val API_TOKEN = "2ROaa2Rh9qu3WVTCms8FoVE4mSfHQHC7QJua95-kKT-PqzIlLSrs4tmHVdtdFw_66-JNfRiJmbCByHTvFNy5dQq-tpfS4FrPpupIzKlgELR3br-r5trpeFhrCRgwWnYx"

    @Singleton
    @Provides
    @WithAuth
    fun providesRetrofitWithAuthenticator(@WithAuth client: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    @Singleton
    @Provides
    @WithAuth
    fun providesTokenAuthenticator(): AuthInterceptor =
        AuthInterceptor()

    @Singleton
    @Provides
    @WithAuth
    fun providesWithAuthOkHttpClient(authenticator: AuthInterceptor): OkHttpClient =
        getOkHttpClient(authInterceptor = authenticator)

    private fun getOkHttpClient(authInterceptor: AuthInterceptor? = null): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .followRedirects(false)
            .followSslRedirects(false)
            .apply { addOptionalLogging() }
            .apply { authInterceptor?.let { addInterceptor(it) } }
            .build()

    private fun OkHttpClient.Builder.addOptionalLogging() {
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            addInterceptor(logging)
        }
    }
}
