package com.example.itemlist3.data

import com.example.itemlist3.dao.ProductDao
import com.example.itemlist3.entity.ProductEntity
import com.example.itemlist3.network.ProductApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductApiService,
    private val dao: ProductDao
) {

    fun getCachedProducts(): Flow<List<ProductEntity>> {
        return dao.getProducts()
    }

    suspend fun fetchProducts() {

        val response = api.getProducts()

        if (response.isSuccessful) {

            val products = response.body() ?: emptyList()

            val entities = products.map {
                ProductEntity(
                    id = it.id,
                    title = it.title,
                    price = it.price,
                    description = it.description,
                    image = it.image
                )
            }

            dao.clearProducts()
            dao.insertProducts(entities)
        }
    }
}