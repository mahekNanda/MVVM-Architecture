    package com.example.itemlist3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.itemlist3.databinding.ItemProductBinding
import com.example.itemlist3.model.Product

class ProductAdapter(
    private val onClick:(Product)->Unit
):ListAdapter<Product,ProductAdapter.ProductViewHolder>(ProductDiffUtil()){

    inner class ProductViewHolder(val binding:ItemProductBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val binding=ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product=getItem(position)

        holder.binding.productName.text=product.name
        holder.binding.productPrice.text=product.price

        Glide.with(holder.itemView.context)
            .load(product.image)
            .into(holder.binding.productImage)
        holder.itemView.setOnClickListener{
            onClick(product)
        }
    }
}