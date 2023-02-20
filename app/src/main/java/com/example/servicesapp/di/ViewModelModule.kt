package com.example.servicesapp.di

import androidx.lifecycle.ViewModel
import com.example.servicesapp.presentation.viewmodels.MainViewModel
import com.example.servicesapp.presentation.viewmodels.ServiceViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ServiceViewModel::class)
    fun bindServiceViewModel(viewModel: ServiceViewModel): ViewModel
}