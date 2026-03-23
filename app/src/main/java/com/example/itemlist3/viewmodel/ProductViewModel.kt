package com.example.itemlist3.viewmodel

import androidx.lifecycle.*
import com.example.itemlist3.data.ProductRepository
import com.example.itemlist3.entity.ProductEntity
import com.example.itemlist3.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _state = MutableLiveData<Resource<List<ProductEntity>>>()

    val state:
            LiveData<Resource<List<ProductEntity>>> = _state

    init {
        observeProducts()
        refresh()
    }

    private fun observeProducts(){
        viewModelScope.launch {
            repository.getProducts().collect{ list -> _state.value = Resource.Success(list) }
        }
    }

    fun refresh() {
        viewModelScope.launch {

            _state.value = Resource.Loading()
            val result = repository.refreshProducts()

            if (result is Resource.Error){
                _state.value = Resource.Error(result.message!!)
            }

           repository.refreshProducts()
            }
        }
    fun retry()
    {
        refresh()
    }
}


