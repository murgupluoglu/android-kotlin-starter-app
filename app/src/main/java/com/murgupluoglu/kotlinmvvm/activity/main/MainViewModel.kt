package com.murgupluoglu.kotlinmvvm.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murgupluoglu.kotlinmvvm.api.ServiceInterface
import com.murgupluoglu.kotlinmvvm.api.response.ProductModel
import com.murgupluoglu.request.Request
import com.murgupluoglu.request.requestFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val service: ServiceInterface
) : ViewModel() {

    private val _uiState = MutableStateFlow<Request<List<ProductModel>>>(Request.Empty)
    val uiState: StateFlow<Request<List<ProductModel>>> get() = _uiState.asStateFlow()

    fun getProducts() {
        viewModelScope.launch {
            requestFlow { service.getProducts() }.collect { response ->
                _uiState.value = response
            }
        }
    }
}