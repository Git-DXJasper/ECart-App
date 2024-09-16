package com.dongze.ecart.view.profile

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dongze.ecart.databinding.VhAddrBinding
import com.dongze.ecart.model.remote.profile.Address

class AddrViewHolder(val binding: VhAddrBinding): ViewHolder(binding.root) {
    fun setData(addr: Address){
        with(binding){
            txtAddrId.text = "addrId[${addr.addrId}]"
            txtTitle.text = addr.title
            txtAddr.text = addr.address
        }
    }
}