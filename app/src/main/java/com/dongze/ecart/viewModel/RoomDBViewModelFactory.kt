package com.dongze.ecart.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RoomDBViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomDBViewModel::class.java)) {
            return RoomDBViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
