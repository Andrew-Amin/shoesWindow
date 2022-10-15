package com.example.shoeswindow.screens.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.shoeswindow.R
import com.example.shoeswindow.constants.KEY_COMPLETED_ONBOARDING_PREF_NAME
import com.example.shoeswindow.constants.KEY_IS_USER_LOGGED_IN
import com.example.shoeswindow.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.loginTv.setOnClickListener { navigateTo(it) }
        binding.sinupBtn.setOnClickListener { navigateTo(it) }

        return binding.root
    }

    private fun navigateTo(view: View) {
        view.findNavController()
            .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }
}