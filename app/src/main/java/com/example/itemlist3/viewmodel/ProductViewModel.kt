package com.example.itemlist3.viewmodel

import androidx.lifecycle.*
import com.example.itemlist3.data.ProductRepository
import com.example.itemlist3.model.Product
import com.example.itemlist3.model.UiState
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val repository = ProductRepository()

    private val _state = MutableLiveData<UiState<List<Product>>>()

    val state: LiveData<UiState<List<Product>>> = _state

    fun loadProducts(){

        viewModelScope.launch {

            _state.value = UiState.Loading

            try{

                val products = repository.getProducts()

                _state.value = UiState.Success(products)

            }catch(e:Exception){

                _state.value = UiState.Error("Failed to load products")

            }
        }
    }
}