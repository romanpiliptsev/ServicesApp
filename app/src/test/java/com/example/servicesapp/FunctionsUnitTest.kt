package com.example.servicesapp

import com.example.servicesapp.data.mapper.ServiceMapper
import com.example.servicesapp.data.network.model.ServiceDto
import com.example.servicesapp.domain.entities.ServiceInfo
import org.junit.Assert.assertEquals
import org.junit.Test

class FunctionsUnitTest {

    private val mapper: ServiceMapper = ServiceMapper()

    @Test
    fun mapDtoToEntity_isCorrect() {
        assertEquals(
            ServiceInfo("name", "desc", "url1", "url2"),
            mapper.mapDtoToEntity(ServiceDto("name", "desc", "url1", "url2"))
        )
    }

    @Test
    fun mapDtoListToEntityList_isCorrect() {
        assertEquals(
            listOf(
                ServiceInfo("name1", "desc1", "url1", "url11"),
                ServiceInfo("name2", "desc2", "url2", "url22"),
                ServiceInfo("name3", "desc3", "url3", "url33")
            ),
            mapper.mapDtoListToEntityList(
                listOf(
                    ServiceDto("name1", "desc1", "url1", "url11"),
                    ServiceDto("name2", "desc2", "url2", "url22"),
                    ServiceDto("name3", "desc3", "url3", "url33")
                )
            )
        )
    }
}