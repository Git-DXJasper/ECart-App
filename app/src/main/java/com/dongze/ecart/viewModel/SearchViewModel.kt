package com.dongze.ecart.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dongze.ecart.model.remote.ApiClient
import com.dongze.ecart.model.remote.SearchService
import com.dongze.ecart.model.remote.dashboard.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {
    private val searchService = ApiClient.retrofit.create(SearchService::class.java)
    private val _searchRes = MutableLiveData<SearchResponse>()
    val searchRes: LiveData<SearchResponse> = _searchRes

    fun searchProduct(keyword: String){
        val call = searchService.searchProduct(keyword)
        call.enqueue(object : Callback<SearchResponse>{
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if(!response.isSuccessful()){
                    Log.d("api_fail","Failed to search, response code: ${response.code()}")
                    return
                }
                val res: SearchResponse? = response.body()
                if(res==null){
                    Log.d("api_fail","empty response!")
                }
                if(res?.status==0){
                    Log.d("api_success","search product success!")
                    _searchRes.value = res
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("api_fail","Error: $t")
            }

        })
    }
}