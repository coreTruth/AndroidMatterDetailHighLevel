package com.example.myapplication.data.source

import com.example.myapplication.data.entity.BusinessResponse
import com.example.myapplication.repository.network.NetworkResource

interface BusinessDataSource {
    suspend fun getBusiness(term: String, location: String): NetworkResource<BusinessResponse>
}
