package com.example.shoeswindow.screens.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.shoeswindow.R
import com.example.shoeswindow.constants.KEY_COMPLETED_ONBOARDING_PREF_NAME
import com.example.shoeswindow.constants.KEY_IS_USER_LOGGED_IN
import com.example.shoeswindow.databinding.FragmentInstructionBinding


class InstructionFragment : Fragment() {
    lateinit var binding: FragmentInstructionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instruction, container, false)
        binding.instructionNextBtn.setOnClickListener {
            it.findNavController().navigate(
                InstructionFragmentDirections.actionInstructionFragmentToShoesListFragment()
            )
        }
        return binding.root

    }

}