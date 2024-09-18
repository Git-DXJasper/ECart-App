package com.dongze.ecart.view.cart

import androidx.recyclerview.widget.RecyclerView
import com.dongze.ecart.databinding.VhOrderBinding
import com.dongze.ecart.model.remote.cart.Order
import com.dongze.ecart.view.CustomOutlineProvider

class OrderViewHolder(val binding: VhOrderBinding): RecyclerView.ViewHolder(binding.root) {
    fun setData(order: Order){
        with(binding){
            txtAddrTitle.text = "addrTitle: ${order.addressTitle}"
            txtAddress.text = order.address
            txtDatetime.text = order.orderDate
            txtOrderId.text = "Order ID[${order.orderId}]"
            txtOrderStatus.text = "Order Status: ${order.orderStatus}"
            txtBillAmount.text = "Bill Amount: $${order.billAmount}"
            txtPayment.text = "Payment Method: ${order.paymentMethod}"

            constraintLayout.elevation = 50f
            val radius = 50f
            constraintLayout.outlineProvider = CustomOutlineProvider(radius)
        }
    }
}