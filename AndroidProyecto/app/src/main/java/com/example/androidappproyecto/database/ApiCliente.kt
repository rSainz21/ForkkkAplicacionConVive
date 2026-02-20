package com.example.androidappproyecto.database

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiCliente {
    private const val BASE_URL = "https://10.0.2.2:8080/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}