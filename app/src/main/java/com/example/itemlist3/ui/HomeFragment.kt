package com.example.itemlist3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.itemlist3.adapter.ProductAdapter
import com.example.itemlist3.databinding.FragmentHomeBinding
import com.example.itemlist3.viewmodel.ProductViewModel
import com.example.itemlist3.model.UiState

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by viewModels()

    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupRecyclerView()

        observeData()

        viewModel.loadProducts()

        return binding.root
    }

    private fun setupRecyclerView(){

        adapter = ProductAdapter { product ->

            val action =
                HomeFragmentDirections
                    .actionHomeFragmentToDetailFragment(product.id)

            findNavController().navigate(action)
        }

        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        binding.recyclerView.adapter = adapter
    }

    private fun observeData(){

        viewModel.state.observe(viewLifecycleOwner){ state ->

            when(state){

                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is UiState.Success -> {

                    binding.progressBar.visibility = View.GONE
                    adapter.submitList(state.data)
                }

                is UiState.Error -> {

                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        state.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}