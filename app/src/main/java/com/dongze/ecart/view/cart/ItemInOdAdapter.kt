package com.dongze.ecart.view.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dongze.ecart.databinding.VhItemInOdBinding
import com.dongze.ecart.model.remote.cart.ItemDetail

class ItemInOdAdapter(private val list: List<ItemDetail>):RecyclerView.Adapter<ItemInOdViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemInOdViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VhItemInOdBinding.inflate(inflater,parent,false)
        return ItemInOdViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ItemInOdViewHolder, position: Int) {
        holder.setData(list[position])
    }
}