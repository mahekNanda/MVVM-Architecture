package com.example.itemlist3.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.itemlist3.R
import com.example.itemlist3.databinding.FragmentDetailBinding


class DetailFragment:Fragment(R.layout.fragment_detail){

    private lateinit var binding:FragmentDetailBinding

    private val args:DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding=FragmentDetailBinding.bind(view)

        val productId=args.productId

        binding.productName.text="Product ID: $productId"

    }
}