package com.example.servicesapp.domain

import androidx.lifecycle.LiveData
import com.example.servicesapp.data.network.model.ServiceDto

interface ServiceRepository {

    suspend fun getServiceList(): LiveData<List<ServiceDto>>

    suspend fun getService(name: String): ServiceDto
}