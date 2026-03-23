package com.example.itemlist3.data

import com.example.itemlist3.dao.ProductDao
import com.example.itemlist3.entity.ProductEntity
import com.example.itemlist3.model.Product
import com.example.itemlist3.network.ProductApiService
import com.example.itemlist3.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductApiService,
    private val dao: ProductDao
) {

     fun getProducts(): Flow<List<ProductEntity>> {
        return dao.getProducts()
    }

    suspend fun refreshProducts(): Resource<Unit> {

        return try {

            val response = api.getProducts()

            if (response.isSuccessful) {

                val body = response.body() ?: emptyList()

                val entities = body.map {
                    ProductEntity(
                        it.id,
                        it.title,
                        it.price,
                        it.description,
                        it.image
                    )
                }

                dao.clearProducts()
                dao.insertProducts(entities)

                Resource.Success(Unit)

            } else {

                when (response.code()) {
                    401 -> Resource.Error("Unauthorized access")
                    404 -> Resource.Error("Data not found")
                    500 -> Resource.Error("Server error")
                    else -> Resource.Error("Something went wrong")
                }
            }

        } catch (e: Exception) {

            Resource.Error("No internet connection")
        }
    }
}