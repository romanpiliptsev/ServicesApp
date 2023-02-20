package com.example.servicesapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.servicesapp.data.network.model.ServiceDto
import com.example.servicesapp.domain.GetServiceListUseCase
import com.example.servicesapp.domain.GetServiceUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getServiceUseCase: GetServiceUseCase,
    private val getServiceListUseCase: GetServiceListUseCase
) : ViewModel() {

    suspend fun getService(name: String): ServiceDto {
        return getServiceUseCase.invoke(name)
    }

    suspend fun getServiceList(): LiveData<List<ServiceDto>> {
        return getServiceListUseCase.invoke()
    }
}