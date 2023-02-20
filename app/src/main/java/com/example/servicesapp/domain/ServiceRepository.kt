package com.example.servicesapp.domain

import com.example.servicesapp.domain.entities.ServiceInfo

interface ServiceRepository {

    suspend fun getServiceList(): List<ServiceInfo>

    suspend fun getService(name: String): ServiceInfo
}