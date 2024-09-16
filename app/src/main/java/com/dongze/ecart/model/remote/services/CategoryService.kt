package com.dongze.ecart.model.remote.services

import com.dongze.ecart.model.remote.category.GetCategoryResponse
import com.dongze.ecart.model.remote.category.GetProductsResponse
import com.dongze.ecart.model.remote.category.GetSubCatByIdResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoryService {
    @GET("Category")
    fun getCategory(): Call<GetCategoryResponse>

    @GET("SubCategory")
    fun getSubCatById(
        @Query("category_id") cid: String
    ): Call<GetSubCatByIdResponse>

    @GET("SubCategory/products/{sub_category_id}")
    fun getProducts(
        @Path("sub_category_id") subCid: String
    ): Call<GetProductsResponse>
}