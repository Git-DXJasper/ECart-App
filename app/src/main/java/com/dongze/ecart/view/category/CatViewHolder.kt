package com.dongze.ecart.view.category

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dongze.ecart.databinding.VhCatBinding
import com.dongze.ecart.model.remote.category.Category
import com.squareup.picasso.Picasso


class CatViewHolder(val binding: VhCatBinding): ViewHolder(binding.root) {
    fun setData(cat: Category){
        with(binding){
            Picasso.get().load("https://apolisrises.co.in/myshop/images/" + cat.imgUrl).into(imgCat)
            txtCatName.text = cat.cname
        }
    }
}