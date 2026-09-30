package com.example.productapp.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productapp.data.model.Product
import com.example.productapp.data.repository.DataResult
import com.example.productapp.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface ProductListUiState {
    data object Loading : ProductListUiState
    data class Success(val products: List<Product>) : ProductListUiState
    data object Empty : ProductListUiState
    data class Error(val message: String) : ProductListUiState
}

class ProductListViewModel(
    private val repository: ProductRepository = ProductRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductListUiState>(ProductListUiState.Loading)
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        _uiState.value = ProductListUiState.Loading
        viewModelScope.launch {
            when (val result = repository.getProducts()) {
                is DataResult.Success -> {
                    _uiState.value = if (result.data.isEmpty()) {
                        ProductListUiState.Empty
                    } else {
                        ProductListUiState.Success(result.data)
                    }
                }
                is DataResult.Error -> {
                    _uiState.value = ProductListUiState.Error(result.message)
                }
            }
        }
    }
}
