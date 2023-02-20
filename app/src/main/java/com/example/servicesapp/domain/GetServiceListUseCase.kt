package com.example.servicesapp.domain

import javax.inject.Inject

class GetServiceListUseCase @Inject constructor(
    private val repository: ServiceRepository
) {
    suspend operator fun invoke() = repository.getServiceList()
}