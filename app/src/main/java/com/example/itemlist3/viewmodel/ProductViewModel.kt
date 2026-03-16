package com.example.itemlist3.viewmodel

import androidx.lifecycle.*
import com.example.itemlist3.data.ProductRepository
import com.example.itemlist3.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState> = _state

    fun loadProducts() {

        viewModelScope.launch {

            _state.value = UiState.Loading

            try {

                val response = repository.getProducts()

                _state.value =
                    UiState.Success(response.body() ?: emptyList())

            } catch (e: Exception) {

                _state.value =
                    UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}