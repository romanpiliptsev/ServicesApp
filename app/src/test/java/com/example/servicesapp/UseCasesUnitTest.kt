package com.example.servicesapp

import com.example.servicesapp.data.mapper.ServiceMapper
import com.example.servicesapp.data.network.ApiService
import com.example.servicesapp.data.network.model.ServiceDto
import com.example.servicesapp.data.network.model.ServicesResponseDto
import com.example.servicesapp.data.repository.ServiceRepositoryImpl
import com.example.servicesapp.domain.usecases.GetServiceListUseCase
import com.example.servicesapp.domain.usecases.GetServiceUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UseCasesUnitTest {

    private val mapper: ServiceMapper = ServiceMapper()

    private val apiMockService = object : ApiService {
        override suspend fun getServices(): ServicesResponseDto {
            return ServicesResponseDto(
                listOf(
                    ServiceDto("name1", "desc1", "url1", "url11"),
                    ServiceDto("name2", "desc2", "url2", "url22"),
                    ServiceDto("name3", "desc3", "url3", "url33")
                )
            )
        }
    }

    private val getServiceUseCase: GetServiceUseCase = GetServiceUseCase(
        ServiceRepositoryImpl(
            apiMockService, mapper
        )
    )

    private val getServiceListUseCase: GetServiceListUseCase = GetServiceListUseCase(
        ServiceRepositoryImpl(
            apiMockService, mapper
        )
    )

    @Test
    fun getService_is_Correct() = runBlocking {
        Assert.assertEquals(
            getServiceUseCase.invoke("name1").name,
            "name1"
        )
    }

    @Test
    fun getService_is_Failure() {
        Assert.assertThrows(
            Exception::class.java,
        ) {
            runBlocking {
                getServiceUseCase.invoke("name4")
            }
        }
    }
}