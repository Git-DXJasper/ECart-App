package com.dongze.ecart.view.category

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dongze.ecart.databinding.VhProductBinding
import com.dongze.ecart.model.remote.category.Product
import com.dongze.ecart.model.remote.dashboard.ProductDetail
import com.squareup.picasso.Picasso

class ProductViewHolder(val binding: VhProductBinding): ViewHolder(binding.root) {
    fun setData(product: Product){
        with(binding){
            txtPid.text = "[${product.pid}]"
            txtPname.text = product.pname
            txtDescription.text = product.description
            txtPrice.text = "$${product.price}"
            Picasso.get().load("https://apolisrises.co.in/myshop/images/" + product.pImgUrl).into(imgProduct)
        }
    }
}