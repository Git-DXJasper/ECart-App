package com.dongze.ecart.view.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dongze.ecart.databinding.VhAddrBinding
import com.dongze.ecart.model.remote.profile.Address

class AddrAdapter(private val addrList: List<Address>): RecyclerView.Adapter<AddrViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddrViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VhAddrBinding.inflate(inflater,parent,false)
        return AddrViewHolder(binding)
    }

    override fun getItemCount() = addrList.size

    override fun onBindViewHolder(holder: AddrViewHolder, position: Int) {
        holder.setData(addrList[position])
    }
}