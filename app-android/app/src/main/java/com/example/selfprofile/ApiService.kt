package com.example.selfprofile

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("hello")
    fun hello(): Call<MessageResponse>

    @POST("updateProfile")
    fun updateProfile(@Body request: UpdateProfileRequest): Call<MessageResponse>
}
