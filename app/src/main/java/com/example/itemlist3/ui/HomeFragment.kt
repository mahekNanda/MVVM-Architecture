package com.example.itemlist3.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itemlist3.adapter.ProductAdapter
import com.example.itemlist3.databinding.FragmentHomeBinding
import com.example.itemlist3.model.Product
import com.example.itemlist3.model.UiState
import com.example.itemlist3.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        observeProducts()

//        observeState()

        viewModel.loadProducts()

        return binding.root
    }

    private fun setupRecyclerView() {

        adapter = ProductAdapter { product ->

            val action =
                HomeFragmentDirections
                    .actionHomeFragmentToDetailFragment(
                        product.id,
                        product.title,
                        product.price.toFloat(),
                        product.description,
                        product.image
                    )

            findNavController().navigate(action)
        }

        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        binding.recyclerView.adapter = adapter
    }


    private fun observeProducts() {

        viewModel.products.observe(viewLifecycleOwner) { products ->

            // 🔴 STOP LOADING HERE
            binding.progressBar.visibility = View.GONE

            if (products.isEmpty()) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                adapter.submitList(products.map {
                    Product(
                        id = it.id,
                        title = it.title,
                        price = it.price,
                        description = it.description,
                        image = it.image
                    )
                })
            }
        }
    }


//    private fun observeState() {
//
//        viewModel.state.observe(viewLifecycleOwner) { state ->
//
//            when (state) {
//
//                is UiState.Loading -> {
//
//                    binding.progressBar.visibility = View.VISIBLE
//                }
//
//                is UiState.Success -> {
//
//                    binding.progressBar.visibility = View.GONE
//                }
//
//                is UiState.Error -> {
//
//                    binding.progressBar.visibility = View.GONE
//
//                    Toast.makeText(
//                        requireContext(),
//                        state.message,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}