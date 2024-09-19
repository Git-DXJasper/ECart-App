package com.dongze.ecart.model.remote.profile

import com.google.gson.annotations.SerializedName

data class LogoutRequest(
    @SerializedName("email_id")
    val emailId: String
)