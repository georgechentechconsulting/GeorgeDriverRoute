package com.example.driverroute

data class Driver(
    val id: Int,
    val name: String,

)

data class Route(
    val id: Int,
    val type: String,
    val name: String
)

data class DriverResponse(
    val drivers: List<Driver>,
    val routes: List<Route>
)
