package com.dongze.ecart.model.local

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object SecuredSPManager {
    private const val SP_FILE_NAME = "safe_sp"
    const val KEY_IS_LOGIN = "is_login"
    const val KEY_ONE_DONE = "one_done"
    const val KEY_TWO_DONE = "two_done"
    const val KEY_THREE_DONE = "three_done"

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
}