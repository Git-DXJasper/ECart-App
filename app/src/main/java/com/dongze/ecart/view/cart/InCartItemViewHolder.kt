package com.dongze.ecart.view.cart

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dongze.ecart.databinding.VhIncartItemBinding
import com.dongze.ecart.model.local.InCartItem

class InCartItemViewHolder(val binding: VhIncartItemBinding):ViewHolder(binding.root) {
    fun setData(incartItem: InCartItem){
        with(binding){
            txtPid.text = incartItem.pid
            txtPname.text = incartItem.pname
            txtPrice.text = incartItem.price
            txtQty.text = "1"
        }
    }
}