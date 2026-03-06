package com.example.itemlist3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.itemlist3.adapter.ProductAdapter
import com.example.itemlist3.databinding.FragmentHomeBinding
import com.example.itemlist3.viewmodel.ProductViewModel
import com.example.itemlist3.R

class HomeFragment : Fragment(R.layout.fragment_home)
{

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel:ProductViewModel by viewModels()

    private lateinit var adapter:ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

        adapter=ProductAdapter{product->

            val action=
                HomeFragmentDirections
                    .actionHomeFragmentToDetailFragment(product.id)

            findNavController().navigate(action)

        }

        binding.recyclerView.layoutManager=
            LinearLayoutManager(requireContext())

        binding.recyclerView.adapter=adapter

        observeData()

        viewModel.loadProducts()

        return binding.root
    }

    private fun observeData(){

        viewModel.productList.observe(viewLifecycleOwner){

            adapter.submitList(it)

        }

    }
}