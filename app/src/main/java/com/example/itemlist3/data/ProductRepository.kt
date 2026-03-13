package com.example.itemlist3.data

import com.example.itemlist3.model.Product
import com.example.itemlist3.network.RetrofitInstance

class ProductRepository {

    suspend fun getProducts(): List<Product> {

        val response = RetrofitInstance.api.getProducts()

        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("API Error: ${response.code()}")
        }
    }
}