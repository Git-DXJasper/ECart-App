package com.dongze.ecart.model.remote.profile

import com.google.gson.annotations.SerializedName

data class AuthUserRequest(
    @SerializedName("email_id")
    val email: String,
    @SerializedName("password")
    val password: String
)