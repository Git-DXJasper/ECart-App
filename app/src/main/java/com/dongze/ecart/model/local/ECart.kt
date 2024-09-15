package com.dongze.ecart.model.local

import android.app.Application

class ECart: Application(){
    override fun onCreate() {
        super.onCreate()
        SecuredSPManager.init(this)
    }
}