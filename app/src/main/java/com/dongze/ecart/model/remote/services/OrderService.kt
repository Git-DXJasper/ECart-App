package com.dongze.ecart.model.remote.services

import com.dongze.ecart.model.remote.cart.PlaceOrderRequest
import com.dongze.ecart.model.remote.cart.PlaceOrderResponse
import com.dongze.ecart.model.remote.cart.FetchOrdersByUserIdResponse
import com.dongze.ecart.model.remote.cart.GetOrderDetailResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OrderService {
    @Headers("Content-type: application/json")
    @POST("Order")
    fun placeOrder(
        @Body input: PlaceOrderRequest
    ): Call<PlaceOrderResponse>

    @GET("Order/userOrders/{user_id}")
    fun fetchOrdersByUserId(
        @Path("user_id") userId: Int
    ): Call<FetchOrdersByUserIdResponse>

    @GET("Order")
    fun getOderDetail(
        @Query("order_id") orderId: Int
    ): Call<GetOrderDetailResponse>
}