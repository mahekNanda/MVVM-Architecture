package com.example.itemlist3.network

import com.example.itemlist3.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getProducts(): Response<List<Product>>
}