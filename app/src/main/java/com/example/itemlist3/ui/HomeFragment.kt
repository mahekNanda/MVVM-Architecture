package com.example.itemlist3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itemlist3.adapter.ProductAdapter
import com.example.itemlist3.databinding.FragmentHomeBinding
import com.example.itemlist3.model.Product
import com.example.itemlist3.utils.Resource
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

        observeState()

        viewModel.refresh()

        binding.btnRetry.setOnClickListener {

            binding.btnRetry.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE

            viewModel.refresh()
        }
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

    private fun observeState() {

        viewModel.state.observe(viewLifecycleOwner) { state ->

            when (state) {

                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnRetry.visibility = View.GONE
                }

                is Resource.Success -> {

                    binding.progressBar.visibility = View.GONE
                    binding.btnRetry.visibility = View.GONE

                    val list = state.data ?: emptyList()

                    adapter.submitList(list.map {
                        Product(
                            it.id,
                            it.title,
                            it.price,
                            it.description,
                            it.image
                        )
                    })
                }

                is Resource.Error -> {

                    binding.progressBar.visibility = View.GONE
                    binding.btnRetry.visibility = View.VISIBLE

                    Toast.makeText(
                        requireContext(),
                        state.message,
                        Toast.LENGTH_LONG
                    ).show()
                    binding.btnRetry.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}