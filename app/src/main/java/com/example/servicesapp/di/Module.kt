package com.example.servicesapp.di

import com.example.servicesapp.data.network.ApiFactory
import com.example.servicesapp.data.network.ApiService
import com.example.servicesapp.data.repository.ServiceRepositoryImpl
import com.example.servicesapp.domain.ServiceRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface Module {

    @Binds
    @ApplicationScope
    fun bindServiceRepository(impl: ServiceRepositoryImpl): ServiceRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}