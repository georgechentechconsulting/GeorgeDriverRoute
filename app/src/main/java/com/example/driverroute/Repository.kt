package com.example.driverroute

import com.example.driverroute.api.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun getDrivers(): List<Driver> {
        val response = apiService.fetchData()
        if (response.isSuccessful && response.body() != null) {
            return response.body()?.drivers ?: emptyList()
        } else {
            throw Exception("getDrivers error")
        }
    }

    suspend fun getRoutes(): List<Route> {
        val response = apiService.fetchData()
        if (response.isSuccessful && response.body() != null) {
            return response.body()?.routes ?: emptyList()
        } else {
            throw Exception("getRoutes routes")
        }
    }
}
