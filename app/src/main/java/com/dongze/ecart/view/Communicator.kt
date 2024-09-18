package com.dongze.ecart.view

import com.dongze.ecart.model.remote.dashboard.ProductDetail

interface Communicator {
    fun sendCid(cid: String)

    fun sendPid(pid: String)

    fun sendPList(plist: List<ProductDetail>)

    fun sendOrderId(orderId: Int)
}