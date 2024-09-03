package com.example.driverroute.api

import com.example.driverroute.DriverResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("data")
    suspend fun fetchData(): Response<DriverResponse>

    companion object {
        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl("https://d49c3a78-a4f2-437d-bf72-569334dea17c.mock.pstmn.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}
