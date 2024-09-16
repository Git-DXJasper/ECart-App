package com.dongze.ecart.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dongze.ecart.model.remote.ApiClient
import com.dongze.ecart.model.remote.services.ProductDetailService
import com.dongze.ecart.model.remote.dashboard.GetDetailByPIdResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailViewModel: ViewModel() {
    private val pDetailService = ApiClient.retrofit.create(ProductDetailService::class.java)
    private val _pDetailRes = MutableLiveData<GetDetailByPIdResponse>()
    val pDetailRes: LiveData<GetDetailByPIdResponse> = _pDetailRes

    fun getDetail(pid: String){
        val call = pDetailService.getDetailByPId(pid)
        call.enqueue(object: Callback<GetDetailByPIdResponse> {
            override fun onResponse(call: Call<GetDetailByPIdResponse>, response: Response<GetDetailByPIdResponse>) {
                if(!response.isSuccessful()){
                    Log.d("api_fail","Failed to search, response code: ${response.code()}")
                    return
                }
                val res: GetDetailByPIdResponse? = response.body()
                if(res==null){
                    Log.d("api_fail","empty response!")
                }
                if(res?.status==0){
                    Log.d("api_success","search product success!")
                    _pDetailRes.value = res
                }
            }

            override fun onFailure(call: Call<GetDetailByPIdResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("api_fail","Error: $t")
            }

        })
    }
}