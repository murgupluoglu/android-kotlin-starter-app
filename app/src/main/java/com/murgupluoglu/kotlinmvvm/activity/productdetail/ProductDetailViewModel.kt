package com.murgupluoglu.kotlinmvvm.activity.productdetail

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
class ProductDetailViewModel @Inject constructor(
    private val service: ServiceInterface
) : ViewModel() {

    private val _uiState = MutableStateFlow<Request<ProductModel>>(Request.Empty)
    val uiState: StateFlow<Request<ProductModel>> get() = _uiState.asStateFlow()

    fun getPerson(productId: String) {
        viewModelScope.launch {
            requestFlow { service.getProduct(productId) }.collect { response ->
                _uiState.value = response
            }
        }
    }
}