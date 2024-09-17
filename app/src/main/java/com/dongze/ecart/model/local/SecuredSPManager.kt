package com.dongze.ecart.model.local

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.dongze.ecart.model.remote.profile.User
import com.google.gson.Gson

object SecuredSPManager {
    private const val SP_FILE_NAME = "safe_sp"
    const val KEY_IS_LOGIN = "is_login"
    const val KEY_ONE_DONE = "one_done"
    const val KEY_TWO_DONE = "two_done"
    const val KEY_THREE_DONE = "three_done"
    const val KEY_USER = "user"
    const val KEY_DELIVERY = "delivery"
    const val KEY_PAYMENT = "payment"

    private lateinit var sp: SharedPreferences

    fun init(context: Context){
        val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        sp = EncryptedSharedPreferences.create(
            SP_FILE_NAME,
            masterKey,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveBoolean(key: String, value: Boolean){
        sp.edit().putBoolean(key,value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean) : Boolean{
        return sp.getBoolean(key,defaultValue)
    }

    fun saveUser(u: User){
        sp.edit().putString(KEY_USER, Gson().toJson(u)).apply()
    }

    fun getUser():User?{
        val userJson = sp.getString(KEY_USER,null)
        return Gson().fromJson(userJson, User::class.java)
    }
    fun saveString(key:String, value:String){
        sp.edit().putString(key,value).apply()
    }
    fun getString(key: String, defaultValue: String):String{
        return sp.getString(key,defaultValue)?:defaultValue
    }
}