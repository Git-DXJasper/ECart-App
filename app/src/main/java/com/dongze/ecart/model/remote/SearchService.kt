package com.dongze.ecart.model.remote

import com.dongze.ecart.model.remote.dashboard.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("Product/search")
    fun searchProduct(
        @Query("query") keyword: String
    ): Call<SearchResponse>
}