package com.example.itemlist3.data

import com.example.itemlist3.R
import com.example.itemlist3.model.Product
import kotlinx.coroutines.delay

class ProductRepository {

    suspend fun getProducts():List<Product>{

        delay(2000) // simulate API delay

        return listOf(

            Product(
                1,
                "Laptop",
                "₹80,000",
                R.drawable.laptop,
                "High performance laptop"
            ),

            Product(
                2,
                "Mobile",
                "₹25,000",
                R.drawable.mobile,
                "Latest smartphone"
            ),

            Product(
                3,
                "Headphones",
                "₹2,000",
                R.drawable.headphone,
                "Wireless headphones"
            )
        )
    }
}