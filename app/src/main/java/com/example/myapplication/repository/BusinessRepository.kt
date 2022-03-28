package com.example.myapplication.repository

import com.example.myapplication.data.entity.BusinessItem
import com.example.myapplication.data.entity.BusinessResponse
import com.example.myapplication.data.source.BusinessDataSource
import com.example.myapplication.repository.network.NetworkResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BusinessRepository(
    private val remoteDataSource: BusinessDataSource,
    private val localDataSource: BusinessDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getBusiness(term: String, location: String): List<BusinessItem> {
        return when(val response = remoteDataSource.getBusiness(term, location)) {
            is NetworkResource.Success -> handleSuccess(response.value)
            is NetworkResource.Failure -> (localDataSource.getBusiness(term, location) as NetworkResource.Success).value.businesses
        }
    }

    private suspend fun handleSuccess(businesses: BusinessResponse): List<BusinessItem>  {
        //insert to db
        return businesses.businesses
    }
}
