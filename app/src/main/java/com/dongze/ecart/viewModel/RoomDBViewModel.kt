package com.dongze.ecart.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dongze.ecart.model.local.AppDatabase
import com.dongze.ecart.model.local.InCartItem
import com.dongze.ecart.model.local.SecuredSPManager
import com.dongze.ecart.model.remote.dashboard.ProductDetail

class RoomDBViewModel(application: Application): AndroidViewModel(application) {
    private var _pDetailLiveData = MutableLiveData<ProductDetail>()
    val pDetailLiveData: LiveData<ProductDetail> = _pDetailLiveData

    private var _inCartItemList = MutableLiveData<List<InCartItem>>()
    val inCartItemList: LiveData<List<InCartItem>> = _inCartItemList

    private val dao by lazy{
        AppDatabase.getInstance(application).getInCartItemDao()
    }

    fun storeInCartItem(pDetail: ProductDetail){
        val userId =  SecuredSPManager.getUser()?.userId?:-1
        val newAddedItem = InCartItem(pDetail.pid,pDetail.pname,pDetail.price,
            1, userId)
        Log.d("RoomDB", "Attempting to insert: $newAddedItem,[userId] is $userId,[pid] is ${pDetail.pid}")
        dao.insertInCartItem(newAddedItem)
    }

    fun getListFromRoom(userID: Int) {
        _inCartItemList.value = dao.getInCartItemList(userID)
    }

    fun sendPDetail(pDetail: ProductDetail){
        _pDetailLiveData.value = pDetail
    }

    fun increaseQTY(inCartItem: InCartItem){
        dao.updateInCartItem(inCartItem)
    }

    fun decreaseQTY(inCartItem: InCartItem){
        dao.updateInCartItem(inCartItem)
    }

    fun removeItem(inCartItem: InCartItem){
        dao.deleteInCartItem(inCartItem)
    }
}