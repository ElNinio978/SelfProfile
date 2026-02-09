package com.example.selfprofile

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.100:8080/") // komputer
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @JvmStatic
    fun getApi(): ApiService {
        return apiService
    }
}
