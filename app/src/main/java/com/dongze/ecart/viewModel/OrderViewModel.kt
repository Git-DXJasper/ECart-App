package com.dongze.ecart.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dongze.ecart.model.local.AppDatabase
import com.dongze.ecart.model.local.InCartItem
import com.dongze.ecart.model.local.SecuredSPManager
import com.dongze.ecart.model.local.SecuredSPManager.KEY_DELIVERY
import com.dongze.ecart.model.local.SecuredSPManager.KEY_PAYMENT
import com.dongze.ecart.model.remote.ApiClient
import com.dongze.ecart.model.remote.cart.DeliveryAddress
import com.dongze.ecart.model.remote.cart.FetchOrdersByUserIdResponse
import com.dongze.ecart.model.remote.cart.GetOrderDetailResponse
import com.dongze.ecart.model.remote.cart.Item
import com.dongze.ecart.model.remote.cart.PlaceOrderRequest
import com.dongze.ecart.model.remote.cart.PlaceOrderResponse
import com.dongze.ecart.model.remote.services.OrderService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderViewModel(application: Application): AndroidViewModel(application) {
    private val orderService = ApiClient.retrofit.create(OrderService::class.java)
    private val _placeOrderRes = MutableLiveData<PlaceOrderResponse>()
    val placeOrderRes: LiveData<PlaceOrderResponse> = _placeOrderRes

    private val _fetchOrderListRes = MutableLiveData<FetchOrdersByUserIdResponse>()
    val fetchOrderListRes: LiveData<FetchOrdersByUserIdResponse> = _fetchOrderListRes

    private val _getOrderDetailRes = MutableLiveData<GetOrderDetailResponse>()
    val getOrderDetailResponse: LiveData<GetOrderDetailResponse> = _getOrderDetailRes

    private val dao by lazy{
        AppDatabase.getInstance(application).getInCartItemDao()
    }

    fun placeOrder(){
        //prepare input for the call
        val title = SecuredSPManager.getString(KEY_DELIVERY,"N/A").substringAfter("[").substringBefore("]")
        val address = SecuredSPManager.getString(KEY_DELIVERY,"N/A").substringAfter(": ").trim()
        val paymentMethod = SecuredSPManager.getString(KEY_PAYMENT,"N/A")
        val userId = SecuredSPManager.getUser()?.userId ?: -1
        val itemList = dao.getInCartItemList(userId)
        var totalPrice = 0
        for(item in itemList){
            totalPrice += (item.price.toInt() * item.qty)
        }
        val items =  itemList.map{ Item(it.pid.toInt(),it.qty,it.price.toInt()) }
        //make the call
        val call = orderService.placeOrder(PlaceOrderRequest(
            billAmount = totalPrice,
            deliveryAddress = DeliveryAddress(title,address),
            items = items, paymentMethod = paymentMethod, userId = userId
        ))
        call.enqueue(object : Callback<PlaceOrderResponse>{
            override fun onResponse(call: Call<PlaceOrderResponse>, response: Response<PlaceOrderResponse>) {
                if(!response.isSuccessful()){
                    Log.d("api_fail","fail to place order, response code: ${response.code()}")
                }
                var res: PlaceOrderResponse? = response.body()
                if(res==null){
                    Log.d("api_fail","empty response!")
                }
                if(res?.status == 0){
                    Log.d("api_success","place order success!")
                    _placeOrderRes.value = res
                    //clear the cart after placing order success
                    dao.clear()
                }
            }

            override fun onFailure(call: Call<PlaceOrderResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("api_fail","place order fail,Error: $t")
            }
        })
    }

    fun fetchOrderList(userId: Int){
        val call = orderService.fetchOrdersByUserId(userId)
        call.enqueue(object : Callback<FetchOrdersByUserIdResponse>{
            override fun onResponse(call: Call<FetchOrdersByUserIdResponse>, response: Response<FetchOrdersByUserIdResponse>) {
                if(!response.isSuccessful()){
                    Log.d("api_fail","fail to fetch order list, response code: ${response.code()}")
                }
                var res: FetchOrdersByUserIdResponse? = response.body()
                if(res==null){
                    Log.d("api_fail","empty response!")
                }
                if(res?.status == 0){
                    Log.d("api_success","fetch order list success!")
                    _fetchOrderListRes.value = res
                }
            }

            override fun onFailure(call: Call<FetchOrdersByUserIdResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("api_fail","fetch order list fail,Error: $t")
            }
        })
    }

    fun getOrderDetail(orderId: Int){
        val call = orderService.getOderDetail(orderId)
        call.enqueue(object : Callback<GetOrderDetailResponse>{
            override fun onResponse(call: Call<GetOrderDetailResponse>, response: Response<GetOrderDetailResponse>) {
                if(!response.isSuccessful()){
                    Log.d("api_fail","fail to get order detail, response code: ${response.code()}")
                }
                var res: GetOrderDetailResponse? = response.body()
                if(res==null){
                    Log.d("api_fail","empty response!")
                }
                if(res?.status == 0){
                    Log.d("api_success","get order detail success!")
                    _getOrderDetailRes.value = res
                }
            }

            override fun onFailure(call: Call<GetOrderDetailResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("api_fail","get order detail fail,Error: $t")
            }
        })
    }
}