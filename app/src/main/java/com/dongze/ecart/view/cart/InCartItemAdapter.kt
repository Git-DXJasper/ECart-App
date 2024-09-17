package com.dongze.ecart.view.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dongze.ecart.databinding.VhIncartItemBinding
import com.dongze.ecart.model.local.InCartItem

class InCartItemAdapter(private val list: List<InCartItem>):RecyclerView.Adapter<InCartItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InCartItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VhIncartItemBinding.inflate(inflater,parent,false)
        return InCartItemViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: InCartItemViewHolder, position: Int) {
        holder.setData(list[position])
    }
}