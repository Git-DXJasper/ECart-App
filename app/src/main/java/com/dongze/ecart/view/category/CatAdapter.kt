package com.dongze.ecart.view.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dongze.ecart.databinding.VhCatBinding
import com.dongze.ecart.model.remote.category.Category
import com.dongze.ecart.view.Communicator

class CatAdapter(private val clist: List<Category>, val communicator: Communicator): RecyclerView.Adapter<CatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VhCatBinding.inflate(inflater,parent,false)
        return CatViewHolder(binding)
    }

    override fun getItemCount() = clist.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.setData(clist[position])
        holder.itemView.setOnClickListener {
            val cid = clist[position].cid
            communicator.sendCid(cid)
        }
    }


}