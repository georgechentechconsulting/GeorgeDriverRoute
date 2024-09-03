package com.example.driverroute

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DriverViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DriverViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DriverViewModel(repository) as T
        }
        throw IllegalArgumentException("")
    }
}
