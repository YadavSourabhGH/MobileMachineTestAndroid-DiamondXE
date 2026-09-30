package com.example.productapp.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productapp.data.model.Product
import com.example.productapp.data.repository.DataResult
import com.example.productapp.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface ProductDetailsUiState {
    data object Loading : ProductDetailsUiState
    data class Success(val product: Product) : ProductDetailsUiState
    data class Error(val message: String) : ProductDetailsUiState
}

class ProductDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: ProductRepository = ProductRepository()
) : ViewModel() {

    private val productId: Int = savedStateHandle.get<String>("productId")?.toIntOrNull() ?: 0

    private val _uiState =
        MutableStateFlow<ProductDetailsUiState>(ProductDetailsUiState.Loading)
    val uiState: StateFlow<ProductDetailsUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    fun load() {
        if (productId <= 0) {
            _uiState.value = ProductDetailsUiState.Error("Invalid product.")
            return
        }
        _uiState.value = ProductDetailsUiState.Loading
        viewModelScope.launch {
            when (val result = repository.getProductById(productId)) {
                is DataResult.Success -> _uiState.value =
                    ProductDetailsUiState.Success(result.data)
                is DataResult.Error -> _uiState.value =
                    ProductDetailsUiState.Error(result.message)
            }
        }
    }
}
