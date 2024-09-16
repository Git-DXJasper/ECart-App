package com.dongze.ecart.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dongze.ecart.model.local.SecuredSPManager
import com.dongze.ecart.model.remote.services.AddressService
import com.dongze.ecart.model.remote.ApiClient
import com.dongze.ecart.model.remote.profile.AddAddressRequest
import com.dongze.ecart.model.remote.profile.AddAddressResponse
import com.dongze.ecart.model.remote.profile.GetAddressListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressViewModel: ViewModel() {
    private val addrService = ApiClient.retrofit.create(AddressService::class.java)
    private val _addAddrRes = MutableLiveData<AddAddressResponse>()
    val addAddrRes: LiveData<AddAddressResponse> = _addAddrRes

    private val _getAddrListRes = MutableLiveData<GetAddressListResponse>()
    val getAddrListRes: LiveData<GetAddressListResponse> = _getAddrListRes

    fun addAddr(userId: Int, title: String, address: String){
        val call = addrService.addAddress(AddAddressRequest(address,title, userId))
        call.enqueue(object: Callback<AddAddressResponse>{
            override fun onResponse(call: Call<AddAddressResponse>, response: Response<AddAddressResponse>) {
                if(!response.isSuccessful()){
                    Log.d("api_fail","fail to add address, response code: ${response.code()}")
                }
                val res: AddAddressResponse? = response.body()
                if(res==null){
                    Log.d("api_fail","empty response!")
                }
                if(res?.status==0){
                    Log.d("api_success","add address success!")
                    _addAddrRes.value = res
                }
            }

            override fun onFailure(call: Call<AddAddressResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("api_fail","add address fail,Error: $t")
            }
        })
    }

    fun getAddrList(){
        val call = addrService.getAddressList(SecuredSPManager.getUser()?.userId?:-1)
        call.enqueue(object : Callback<GetAddressListResponse>{
            override fun onResponse(
                call: Call<GetAddressListResponse>,
                response: Response<GetAddressListResponse>
            ) {
                if(!response.isSuccessful()){
                    Log.d("api_fail","fail to get addr list, response code: ${response.code()}")
                }
                val res: GetAddressListResponse? = response.body()
                if(res==null){
                    Log.d("api_fail","empty response!")
                }
                if(res?.status==0){
                    Log.d("api_success","get addr list success!")
                    _getAddrListRes.value = res
                }
            }

            override fun onFailure(call: Call<GetAddressListResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("api_fail","get addr list fail,Error: $t")
            }

        })
    }
}