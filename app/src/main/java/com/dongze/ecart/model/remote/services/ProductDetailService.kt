package com.dongze.ecart.model.remote.services

import com.dongze.ecart.model.remote.dashboard.GetDetailByPIdResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductDetailService {
    @GET("Product/details/{product_id}")
    fun getDetailByPId(
        @Path("product_id") pid: String
    ): Call<GetDetailByPIdResponse>
}