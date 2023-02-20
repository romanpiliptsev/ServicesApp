package com.example.servicesapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.servicesapp.domain.entities.ServiceInfo
import com.example.servicesapp.domain.usecases.GetServiceUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class ServiceViewModel @Inject constructor(
    private val getServiceUseCase: GetServiceUseCase
) : ViewModel() {
    private val _getServiceStateLiveData = MutableLiveData<GetServiceState>()
    val getServiceStateLiveData: LiveData<GetServiceState>
        get() = _getServiceStateLiveData

    sealed interface GetServiceState {
        object Error : GetServiceState
        object Loading : GetServiceState
        class Loaded(val service: ServiceInfo) : GetServiceState
    }

    private val getServiceHandler = CoroutineExceptionHandler { _, _ ->
        _getServiceStateLiveData.value = GetServiceState.Error
    }

    fun getService(name: String) {
        _getServiceStateLiveData.value = GetServiceState.Loading

        viewModelScope.launch(getServiceHandler) {
            val service = getServiceUseCase.invoke(name)
            _getServiceStateLiveData.value = GetServiceState.Loaded(service)
        }
    }
}