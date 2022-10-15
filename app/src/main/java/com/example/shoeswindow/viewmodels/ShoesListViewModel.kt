package com.example.shoeswindow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoeswindow.models.Shoe

class ShoesListViewModel : ViewModel() {
    private val _shoesList = MutableLiveData<MutableList<Shoe>>()
    val shoesList: LiveData<MutableList<Shoe>> get() = _shoesList
    private var _shoeName = ""
    private var _shoeCompany = ""
    private var _shoeSize = 0
    private var _shoeDesc = ""
    private var _showInvalidDataError = MutableLiveData<Boolean>()
    val showInvalidDataError: LiveData<Boolean> get() = _showInvalidDataError
    private var _shouldNavigateBack = MutableLiveData<Boolean>()
    val shouldNavigateBack: LiveData<Boolean> get() = _shouldNavigateBack


    fun setName(name: String) {
        _shoeName = name
    }

    fun setCompany(company: String) {
        _shoeCompany = company
    }

    fun setSize(size: String) {
        _shoeSize = size.toInt()
    }

    fun setDescription(desc: String) {
        _shoeDesc = desc
    }

    fun resetNavigation() {
        _shouldNavigateBack.value = false
    }


    init {
        _shoesList.value = arrayListOf()
        _showInvalidDataError.value = false
        _shouldNavigateBack.value = false
    }

    fun addToShoesList() {
        if (validateData()) {
            _shoesList.value?.add(
                Shoe(
                    name = _shoeName,
                    company = _shoeCompany,
                    size = _shoeSize,
                    description = _shoeDesc
                )
            )
            resetFields()
            navigateBack()
        } else {
            _showInvalidDataError.value = true
        }

    }

    private fun resetFields() {
        _shoeName = ""
        _shoeCompany = ""
        _shoeSize = 0
        _shoeDesc = ""
        _showInvalidDataError.value = false
        _showInvalidDataError.value = false
    }

    private fun validateData(): Boolean {
        if (_shoeName.isEmpty() || _shoeCompany.isEmpty() || _shoeDesc.isEmpty() || _shoeSize <= 0)
            return false
        return true
    }

    fun navigateBack() {
        _shouldNavigateBack.value = true
    }

}