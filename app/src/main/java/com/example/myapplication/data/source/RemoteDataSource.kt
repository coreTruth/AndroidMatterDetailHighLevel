package com.example.myapplication.data.source

import com.example.myapplication.data.entity.BusinessResponse
import com.example.myapplication.repository.network.ApiInterface
import com.example.myapplication.repository.network.NetworkResource
import com.example.myapplication.repository.network.SafeApiCall

class RemoteDataSource internal constructor(
    private val api: ApiInterface,
): SafeApiCall, BusinessDataSource{
    override suspend fun getBusiness(term: String, location: String): NetworkResource<BusinessResponse> =
        safeApiCall { api.searchBusiness(term, location) }
}
