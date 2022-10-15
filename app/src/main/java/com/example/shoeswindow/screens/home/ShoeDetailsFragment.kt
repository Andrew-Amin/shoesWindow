package com.example.shoeswindow.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.shoeswindow.R
import com.example.shoeswindow.databinding.FragmentShoeDetailsBinding
import com.example.shoeswindow.viewmodels.ShoesListViewModel

class ShoeDetailsFragment : Fragment() {


    private val viewModel: ShoesListViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)

        viewModel.showInvalidDataError.observe(viewLifecycleOwner) { hasInvalidData ->
            if (hasInvalidData)
                showToast()

        }
        viewModel.shouldNavigateBack.observe(viewLifecycleOwner) { navigate ->
            if (navigate){
                requireView().findNavController().navigateUp()
                viewModel.resetNavigation()
            }
        }
        binding.shoesViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


    private fun showToast() = Toast.makeText(context, "Not enough data", Toast.LENGTH_SHORT).show()
}