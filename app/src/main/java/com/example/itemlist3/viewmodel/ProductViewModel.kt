package com.example.itemlist3.viewmodel

import androidx.lifecycle.*
import com.example.itemlist3.data.ProductRepository
import com.example.itemlist3.entity.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    // 🔥 Always observe Room
    val products: LiveData<List<ProductEntity>> =
        repository.getProducts().asLiveData()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            repository.refreshProducts()
        }
    }
}