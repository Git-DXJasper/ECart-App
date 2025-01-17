package com.dongze.ecart.viewModel

import android.media.tv.TvContract.Channels.Logo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dongze.ecart.model.remote.ApiClient
import com.dongze.ecart.model.remote.services.LoginService
import com.dongze.ecart.model.remote.profile.AuthUserRequest
import com.dongze.ecart.model.remote.profile.AuthUserResponse
import com.dongze.ecart.model.remote.profile.LogoutRequest
import com.dongze.ecart.model.remote.profile.LogoutResponse
import com.dongze.ecart.model.remote.profile.RegisterRequest
import com.dongze.ecart.model.remote.profile.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {
    private val loginService = ApiClient.retrofit.create(LoginService::class.java)
    private val _registerRes = MutableLiveData<RegisterResponse>()
    val registerRes: LiveData<RegisterResponse> = _registerRes

    private val _authUserRes = MutableLiveData<AuthUserResponse>()
    val authUserRes: LiveData<AuthUserResponse> = _authUserRes

    private val _logoutRes = MutableLiveData<LogoutResponse>()
    val logoutRes: LiveData<LogoutResponse> = _logoutRes

    fun signup(email: String, password: String, name: String, phone: String){
        val call = loginService.registerUser(RegisterRequest(email, name, phone, password))
        call.enqueue(object :Callback<RegisterResponse>{
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if(!response.isSuccessful()){
                    Log.d("api_fail","fail to signup, response code: ${response.code()}")
                }
                val res: RegisterResponse? = response.body()
                if(res==null){
                    Log.d("api_fail","empty response!")
                }
                if(res?.status==0){
                    Log.d("api_success","register success!")
                    _registerRes.value = res
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("api_fail","register fail,Error: $t")
            }
        })
    }

    fun login(email: String, password: String){
        val call: Call<AuthUserResponse> = loginService.authorizeUser(AuthUserRequest(email, password))
        call.enqueue(object : Callback<AuthUserResponse> {
            override fun onResponse(
                call: Call<AuthUserResponse>,
                response: Response<AuthUserResponse>
            ) {
                if(!response.isSuccessful()){
                    Log.d("api_fail","fail to login, response code: ${response.code()}")
                }
                val res: AuthUserResponse? = response.body()
                if(res==null){
                    Log.d("api_fail","empty response!")
                }
                if(res?.status==0){
                    Log.d("api_success","login success!")
                    _authUserRes.value = res
                }
            }
            override fun onFailure(call: Call<AuthUserResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("api_fail","login fail,Error: $t")
            }
        })
    }

    fun logout(emailId: String){
        val call = loginService.logoutUser(LogoutRequest(emailId))
        call.enqueue(object : Callback<LogoutResponse>{
            override fun onResponse(call: Call<LogoutResponse>, response: Response<LogoutResponse>) {
                if(!response.isSuccessful()){
                    Log.d("api_fail","fail to logout, response code: ${response.code()}")
                }
                val res: LogoutResponse? = response.body()
                if(res==null){
                    Log.d("api_fail","empty response!")
                }
                if(res?.status==0){
                    Log.d("api_success","logout success!")
                    _logoutRes.value = res
                }
            }

            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("api_fail","login fail,Error: $t")
            }
        })
    }
}