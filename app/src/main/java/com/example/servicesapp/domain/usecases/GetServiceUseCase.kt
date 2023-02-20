package com.example.servicesapp.domain.usecases

import com.example.servicesapp.domain.ServiceRepository
import javax.inject.Inject

class GetServiceUseCase @Inject constructor(
    private val repository: ServiceRepository
) {
    suspend operator fun invoke(name: String) = repository.getService(name)
}