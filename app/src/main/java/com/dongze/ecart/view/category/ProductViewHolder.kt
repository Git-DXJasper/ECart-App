package com.dongze.ecart.view.category

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dongze.ecart.databinding.VhProductBinding
import com.dongze.ecart.model.remote.category.Product
import com.dongze.ecart.model.remote.dashboard.ProductDetail
import com.squareup.picasso.Picasso

class ProductViewHolder(val binding: VhProductBinding): ViewHolder(binding.root) {
    fun setData(p: Product){
        with(binding){
            txtPid.text = "[${p.pid}]"
            txtPname.text = p.pname
            txtDescription.text = p.description
            txtPrice.text = "$${p.price}"
            Picasso.get().load("https://apolisrises.co.in/myshop/images/" + p.pImgUrl).into(imgProduct)
        }
    }
}