package com.example.servicesapp.data.repository

import com.example.servicesapp.data.mapper.ServiceMapper
import com.example.servicesapp.data.network.ApiService
import com.example.servicesapp.data.network.model.ServiceDto
import com.example.servicesapp.domain.ServiceRepository
import com.example.servicesapp.domain.entities.ServiceInfo
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: ServiceMapper
) : ServiceRepository {

    private var list: List<ServiceDto>? = null

    override suspend fun getServiceList(): List<ServiceInfo> {
        if (list == null) {
            list = apiService.getServices().items
        }
        return mapper.mapDtoListToEntityList(list ?: listOf())
    }

    override suspend fun getService(name: String): ServiceInfo {
        if (list == null) {
            list = apiService.getServices().items
        }
        return mapper.mapDtoToEntity(
            list?.find {
                it.name == name
            } ?: throw Exception()
        )
    }
}