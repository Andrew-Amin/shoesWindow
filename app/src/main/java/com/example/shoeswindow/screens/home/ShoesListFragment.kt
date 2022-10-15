package com.example.shoeswindow.screens.home

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.shoeswindow.R
import com.example.shoeswindow.constants.KEY_IS_USER_LOGGED_IN
import com.example.shoeswindow.databinding.FragmentShoesListBinding
import com.example.shoeswindow.databinding.ShoesItemViewBinding
import com.example.shoeswindow.models.Shoe
import com.example.shoeswindow.viewmodels.ShoesListViewModel
import android.widget.LinearLayout as WidgetLinearLayout

class ShoesListFragment : Fragment() {

    lateinit var binding: FragmentShoesListBinding
    lateinit var shoesListLinearLayout: WidgetLinearLayout
    private val viewModel: ShoesListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init(inflater, container)

        binding.addShoesFab.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailsFragment()
            )
        )

        viewModel.shoesList.observe(viewLifecycleOwner) { shoesList ->
            if (!shoesList.isNullOrEmpty()) {
                //TODO: Need to be improved .. this Approach redraw all the shoesList each time a new entry has been added to it
                for (item in shoesList)
                    generateShoesItem(item)
            }

        }
        return binding.root
    }

    private fun init(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_list, container, false)
        shoesListLinearLayout = binding.shoesListLinearLayout
        addMenu()
    }

    private fun addMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.shoes_list_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.loginFragment) {
                    view!!.findNavController().navigate(menuItem.itemId)
                    return true
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun generateShoesItem(item: Shoe) {
        val newItemViewBinding =
            DataBindingUtil.inflate<ShoesItemViewBinding>(
                layoutInflater,
                R.layout.shoes_item_view,
                binding.shoesListLinearLayout,
                false
            )
        newItemViewBinding.shoesItem = item

        shoesListLinearLayout.addView(newItemViewBinding.root)
    }
}