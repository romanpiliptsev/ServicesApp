package com.example.servicesapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.servicesapp.domain.entities.ServiceInfo
import com.example.servicesapp.domain.usecases.GetServiceListUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getServiceListUseCase: GetServiceListUseCase
) : ViewModel() {

    private val _getServiceListStateLiveData = MutableLiveData<GetServiceListState>()
    val getServiceListStateLiveData: LiveData<GetServiceListState>
        get() = _getServiceListStateLiveData

    sealed interface GetServiceListState {
        object Error : GetServiceListState
        object Loading : GetServiceListState
        class Loaded(val serviceList: List<ServiceInfo>) : GetServiceListState
    }

    private val getServiceListHandler = CoroutineExceptionHandler { _, exception ->
        _getServiceListStateLiveData.value = GetServiceListState.Error
    }

    fun getServiceList() {
        _getServiceListStateLiveData.value = GetServiceListState.Loading

        viewModelScope.launch(getServiceListHandler) {
            val list = getServiceListUseCase.invoke()
            _getServiceListStateLiveData.value = GetServiceListState.Loaded(list)
        }
    }
}