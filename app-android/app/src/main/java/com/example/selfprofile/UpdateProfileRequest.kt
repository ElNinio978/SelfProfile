package com.example.selfprofile

data class UpdateProfileRequest(
    val fullName: String,
    val username: String,
    val bio: String
)
