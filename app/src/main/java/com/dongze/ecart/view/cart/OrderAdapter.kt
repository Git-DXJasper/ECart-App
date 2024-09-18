package com.dongze.ecart.view.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dongze.ecart.databinding.VhOrderBinding
import com.dongze.ecart.model.remote.cart.Order
import com.dongze.ecart.view.Communicator

class OrderAdapter(private val orderList: List<Order>, private val communicator: Communicator):RecyclerView.Adapter<OrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VhOrderBinding.inflate(inflater,parent,false)
        return OrderViewHolder(binding)
    }

    override fun getItemCount() = orderList.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.setData(orderList[position])
        holder.itemView.setOnClickListener {
            val orderId = orderList[position].orderId.toInt()
            communicator.sendOrderId(orderId)
        }
    }
}