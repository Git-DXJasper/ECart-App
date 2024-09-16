package com.dongze.ecart.model.remote.services

import com.dongze.ecart.model.remote.profile.AddAddressRequest
import com.dongze.ecart.model.remote.profile.AddAddressResponse
import com.dongze.ecart.model.remote.profile.GetAddressListResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface AddressService {
    @Headers("Content-type: application/json")
    @POST("User/address")
    fun addAddress(
        @Body addAddressRequest: AddAddressRequest
    ): Call<AddAddressResponse>

    @GET("User/addresses/{user_id}")
    fun getAddressList(
        @Path("user_id") userId: Int
    ): Call<GetAddressListResponse>
}