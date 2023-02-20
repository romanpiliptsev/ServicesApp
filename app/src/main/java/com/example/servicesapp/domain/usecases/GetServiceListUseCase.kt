package com.example.servicesapp.domain.usecases

import com.example.servicesapp.domain.ServiceRepository
import javax.inject.Inject

class GetServiceListUseCase @Inject constructor(
    private val repository: ServiceRepository
) {
    suspend operator fun invoke() = repository.getServiceList()
}