package com.example.itemlist3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.itemlist3.data.ProductRepository
import com.example.itemlist3.model.Product

class ProductViewModel : ViewModel() {

    private val repository = ProductRepository()

    private val _productList = MutableLiveData<List<Product>>()

    val productList:LiveData<List<Product>> = _productList

    fun loadProducts(){

        _productList.value = repository.getProducts()

    }

}