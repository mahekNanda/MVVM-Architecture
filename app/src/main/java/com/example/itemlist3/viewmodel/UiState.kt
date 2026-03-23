package com.example.itemlist3.viewmodel

import com.example.itemlist3.model.Product

sealed class UiState {

    object Loading : UiState()
    data class Success(val data: List<Product>): UiState()
    data class Error(val message: String): UiState()

}