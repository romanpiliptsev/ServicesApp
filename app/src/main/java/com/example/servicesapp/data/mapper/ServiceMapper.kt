package com.example.servicesapp.data.mapper

import com.example.servicesapp.data.network.model.ServiceDto
import com.example.servicesapp.domain.entities.ServiceInfo
import javax.inject.Inject

class ServiceMapper @Inject constructor() {
    fun mapDtoToEntity(dto: ServiceDto) = ServiceInfo(
        dto.name,
        dto.description,
        dto.iconUrl,
        dto.serviceUrl
    )

    fun mapDtoListToEntityList(list: List<ServiceDto>) = list.map {
        mapDtoToEntity(it)
    }
}