package com.example.driverroute

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DriverViewModel(private val repository: Repository) : ViewModel() {

    private val _drivers = MutableLiveData<List<Driver>>()
    val drivers: LiveData<List<Driver>> get() = _drivers

    private val _routes = MutableLiveData<List<Route>>()
    val routes: LiveData<List<Route>> get() = _routes

    init {
        fetchDrivers()
        fetchRoutes()
    }

    private fun fetchDrivers() {
        viewModelScope.launch {
            try {
                val driverList = repository.getDrivers()
                _drivers.value = driverList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun fetchRoutes() {
        viewModelScope.launch {
            try {
                val routeList = repository.getRoutes()
                _routes.value = routeList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun sortDrivers() {
        _drivers.value = _drivers.value?.sortedBy { driver ->
            driver.name.split(" ").lastOrNull() ?: ""
        }
    }

    fun getRouteForDriver(driverId: Int): Route? {
        val routesList = _routes.value ?: return null
        return when {
            routesList.any { it.id == driverId } -> routesList.find { it.id == driverId }
            driverId % 2 == 0 -> routesList.firstOrNull { it.type == "R" }
            driverId % 5 == 0 -> routesList.drop(1).firstOrNull { it.type == "C" }
            else -> routesList.lastOrNull { it.type == "I" }
        }
    }
}
