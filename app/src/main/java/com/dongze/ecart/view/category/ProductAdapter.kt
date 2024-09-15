package com.dongze.ecart.view.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dongze.ecart.databinding.VhProductBinding
import com.dongze.ecart.model.remote.category.Product
import com.dongze.ecart.view.Communicator

class ProductAdapter(private val plist: List<Product>, val communicator: Communicator): RecyclerView.Adapter<ProductViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VhProductBinding.inflate(inflater,parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount() = plist.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.setData(plist[position])
        holder.itemView.setOnClickListener {
            val pid = plist[position].pid
            communicator.sendPid(pid)
        }
    }
}