package com.example.itemlist3.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.itemlist3.model.Product

class ProductDiffUtil:DiffUtil.ItemCallback<Product>(){

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {

        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {

        return oldItem==newItem
    }
}