package com.example.servicesapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.servicesapp.domain.GetServiceListUseCase
import com.example.servicesapp.domain.GetServiceUseCase
import java.lang.RuntimeException
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val getServiceUseCase: GetServiceUseCase,
    private val getServiceListUseCase: GetServiceListUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == MainViewModel::class.java) {
            return MainViewModel(getServiceUseCase, getServiceListUseCase) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}