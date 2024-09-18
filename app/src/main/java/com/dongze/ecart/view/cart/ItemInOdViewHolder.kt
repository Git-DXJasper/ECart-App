package com.dongze.ecart.view.cart

import androidx.recyclerview.widget.RecyclerView
import com.dongze.ecart.databinding.VhItemInOdBinding
import com.dongze.ecart.model.remote.cart.ItemDetail
import com.dongze.ecart.view.CustomOutlineProvider
import com.squareup.picasso.Picasso

class ItemInOdViewHolder(val binding: VhItemInOdBinding): RecyclerView.ViewHolder(binding.root) {
    fun setData(itemDetail: ItemDetail){
        with(binding){
            itemDetail.apply {
                txtPid.text = "Product ID: $productId"
                txtQty.text = "Quantity: $quantity"
                txtUnitPrice.text = "Unit Price: $unitPrice"
                val amount = quantity.toInt() * unitPrice.toInt()
                txtAmount.text = "Amount: $amount"
                txtPname.text = "Product Name: $productName"
                txtDesc.text = "Description: ${description.substring(0,22)}......"
                Picasso.get().load("https://apolisrises.co.in/myshop/images/" + productImageUrl).into(imgItemInOd)

                constraintLayout.elevation = 50f
                val radius = 50f
                constraintLayout.outlineProvider = CustomOutlineProvider(radius)
            }
        }
    }
}