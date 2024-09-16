package com.dongze.ecart.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dongze.ecart.model.remote.ApiClient
import com.dongze.ecart.model.remote.services.CategoryService
import com.dongze.ecart.model.remote.category.GetCategoryResponse
import com.dongze.ecart.model.remote.category.GetProductsResponse
import com.dongze.ecart.model.remote.category.GetSubCatByIdResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel: ViewModel() {
    private val catService = ApiClient.retrofit.create(CategoryService::class.java)
    private val _getCatRes = MutableLiveData<GetCategoryResponse>()
    val getCatRes: LiveData<GetCategoryResponse> = _getCatRes

    private val _getSubCatRes = MutableLiveData<GetSubCatByIdResponse>()
    val getSubCatRes: LiveData<GetSubCatByIdResponse> = _getSubCatRes

    private val _getPListRes = MutableLiveData<GetProductsResponse>()
    val getPListRes: LiveData<GetProductsResponse> = _getPListRes

    fun getCatList(){
        val call = catService.getCategory()
        call.enqueue(object :Callback<GetCategoryResponse>{
            override fun onResponse(call: Call<GetCategoryResponse>, response: Response<GetCategoryResponse>) {
                if(!response.isSuccessful()){
                    Log.d("api_fail","Failed to get categories, response code: ${response.code()}")
                    return
                }
                val res: GetCategoryResponse? = response.body()
                if(res==null){
                    Log.d("api_fail","empty response!")
                }
                if(res?.status==0){
                    Log.d("api_success","get category list success!")
                    _getCatRes.value = res
                }
            }

            override fun onFailure(call: Call<GetCategoryResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("api_fail","Error: $t")
            }
        })
    }

    fun getSubCatList(cid: String){
        val call = catService.getSubCatById(cid)
        call.enqueue(object :Callback<GetSubCatByIdResponse>{
            override fun onResponse(call: Call<GetSubCatByIdResponse>, response: Response<GetSubCatByIdResponse>) {
                if(!response.isSuccessful()){
                    Log.d("api_fail","Failed to get subcat list, response code: ${response.code()}")
                    return
                }
                val res: GetSubCatByIdResponse? = response.body()
                if(res==null){
                    Log.d("api_fail","empty response!")
                }
                if(res?.status==0){
                    Log.d("api_success","get subcat list success!")
                    _getSubCatRes.value = res
                }
            }

            override fun onFailure(call: Call<GetSubCatByIdResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("api_fail","Error: $t")
            }

        })
    }

    fun getPList(subCid: String){
        val call = catService.getProducts(subCid)
        call.enqueue(object: Callback<GetProductsResponse>{
            override fun onResponse(call: Call<GetProductsResponse>, response: Response<GetProductsResponse>) {
                if(!response.isSuccessful()){
                    Log.d("api_fail","Failed to get plist, response code: ${response.code()}")
                    return
                }
                val res: GetProductsResponse? = response.body()
                if(res==null){
                    Log.d("api_fail","empty response!")
                }
                if(res?.status==0){
                    Log.d("api_success","get plist success!")
                    _getPListRes.value = res
                }
            }

            override fun onFailure(call: Call<GetProductsResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("api_fail","Error: $t")
            }

        })
    }
}