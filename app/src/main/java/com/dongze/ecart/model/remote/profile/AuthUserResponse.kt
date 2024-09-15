package com.dongze.ecart.model.remote.profile

import com.google.gson.annotations.SerializedName

data class AuthUserResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("user")
    val user: User
)