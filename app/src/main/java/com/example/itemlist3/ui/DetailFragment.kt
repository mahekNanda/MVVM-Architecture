package com.example.itemlist3.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.itemlist3.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.productName.text = args.title
        binding.productPrice.text = "₹${args.price}"
        binding.productDescription.text = args.description

        Glide.with(requireContext())
            .load(args.image)
            .into(binding.productImage)

        binding.btnBack.setOnClickListener {

            findNavController().navigateUp()
        }

        binding.btnAddToCart.setOnClickListener {

            Toast.makeText(
                requireContext(),
                "Added to Cart",
                Toast.LENGTH_SHORT
            ).show()
        }

        return binding.root
    }
}