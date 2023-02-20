package com.example.servicesapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.servicesapp.data.network.ApiService
import com.example.servicesapp.data.network.model.ServiceDto
import com.example.servicesapp.domain.ServiceRepository
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ServiceRepository {

    private var list: List<ServiceDto>? = null

    override suspend fun getServiceList(): LiveData<List<ServiceDto>> {
        if (list == null) {
            list = apiService.getServices().items
        }
        val ld = MutableLiveData<List<ServiceDto>>()
        ld.postValue(list)
        return ld
    }

    override suspend fun getService(name: String): ServiceDto {
        if (list == null) {
            list = apiService.getServices().items
        }
        return list?.find {
            it.name == name
        } ?: throw Exception()
    }
}