package com.example.myapplication.data.source

import com.example.myapplication.data.BusinessDao
import com.example.myapplication.data.entity.BusinessItem
import com.example.myapplication.data.entity.BusinessResponse
import com.example.myapplication.repository.network.NetworkResource

class LocalDataSource internal constructor(
    private val businessDao: BusinessDao,
) : BusinessDataSource {
    override suspend fun getBusiness(
        term: String,
        location: String
    ): NetworkResource<BusinessResponse> = NetworkResource.Success(
        BusinessResponse(emptyList())
    )
    //NetworkResource.Success(businessDao.getAllBusiness())
}
