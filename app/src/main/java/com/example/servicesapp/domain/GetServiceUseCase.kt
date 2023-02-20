package com.example.servicesapp.domain

import javax.inject.Inject

class GetServiceUseCase @Inject constructor(
    private val repository: ServiceRepository
) {
    suspend operator fun invoke(name: String) = repository.getService(name)
}