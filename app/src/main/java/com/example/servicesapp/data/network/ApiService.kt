package com.example.servicesapp.data.network

import com.example.servicesapp.data.network.model.ServicesResponseDto
import retrofit2.http.GET

interface ApiService {
    @GET(END_OF_URL)
    suspend fun getServices(): ServicesResponseDto

    companion object {
        private const val END_OF_URL = "semi-final-data.json"
    }
}