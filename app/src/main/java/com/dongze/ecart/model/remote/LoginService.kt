package com.dongze.ecart.model.remote

import com.dongze.ecart.model.remote.profile.AuthUserRequest
import com.dongze.ecart.model.remote.profile.AuthUserResponse
import com.dongze.ecart.model.remote.profile.RegisterRequest
import com.dongze.ecart.model.remote.profile.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @Headers("Content-type: application/json")
    @POST("User/register")
    fun registerUser(
        @Body registerRequest: RegisterRequest
    ): Call<RegisterResponse>

    @Headers("Content-type: application/json")
    @POST("User/auth")
    fun authorizeUser(
        @Body authUserRequest: AuthUserRequest
    ): Call<AuthUserResponse>
}