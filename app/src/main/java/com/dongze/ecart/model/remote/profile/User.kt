package com.dongze.ecart.model.remote.profile

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email_id")
    val email: String,
    @SerializedName("full_name")
    val name: String,
    @SerializedName("mobile_no")
    val phone: String,
    @SerializedName("user_id")
    val userId: Int
)