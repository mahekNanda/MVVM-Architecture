package com.example.itemlist3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.itemlist3.data.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    val products = repository
        .getCachedProducts()
        .asLiveData()

    fun loadProducts() {

        viewModelScope.launch {

            try {
                repository.fetchProducts()
            } catch (e: Exception) {
                // offline fallback
            }
        }
    }
}