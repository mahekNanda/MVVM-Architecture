package com.example.itemlist3.data

import com.example.itemlist3.R
import com.example.itemlist3.model.Product

class ProductRepository {

    fun getProducts():List<Product>{

        return listOf(

            Product(
                1,
                "Laptop",
                "₹80,000",
                R.drawable.laptop,
                "High performance laptop for development and gaming."
            ),

            Product(
                2,
                "Mobile",
                "₹25,000",
                R.drawable.mobile,
                "Latest smartphone with excellent camera."
            ),

            Product(
                3,
                "Headphones",
                "₹2,000",
                R.drawable.headphone,
                "Wireless headphones with deep bass."
            ),

            Product(
                4,
                "Smartwatch",
                "₹5,000",
                R.drawable.watch,
                "Track health and notifications."
            ),

            Product(
                5,
                "Tablet",
                "₹30,000",
                R.drawable.tablet,
                "Large display tablet for study and entertainment."
            )
        )
    }
}