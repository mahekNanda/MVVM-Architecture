package com.example.itemlist3.data

import com.example.itemlist3.network.ProductApiService
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductApiService
) {

    suspend fun getProducts() =
        api.getProducts()
}