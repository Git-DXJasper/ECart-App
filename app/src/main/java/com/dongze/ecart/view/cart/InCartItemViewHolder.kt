package com.dongze.ecart.view.cart

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dongze.ecart.databinding.VhIncartItemBinding
import com.dongze.ecart.model.local.InCartItem
import com.dongze.ecart.view.CustomOutlineProvider
import com.dongze.ecart.viewModel.RoomDBViewModel

class InCartItemViewHolder(
    val binding: VhIncartItemBinding,
    private val roomDBVM: RoomDBViewModel
):ViewHolder(binding.root) {
    fun setData(incartItem: InCartItem){
        with(binding){
            txtPid.text = "[${incartItem.pid}]"
            txtPname.text = incartItem.pname
            txtPrice.text = incartItem.price
            txtQty.text = incartItem.qty.toString()

            btnIncreaseQty.setOnClickListener {
                val newQty = txtQty.text.toString().toInt() + 1
                txtQty.text = newQty.toString()
                roomDBVM.increaseQTY(incartItem.copy(qty = newQty))  // Update with new quantity
            }

            btnDecreaseQty.setOnClickListener {
                val currentQty = txtQty.text.toString().toInt()
                if(currentQty > 1) {
                    val newQty = currentQty - 1
                    txtQty.text = newQty.toString()
                    roomDBVM.decreaseQTY(incartItem.copy(qty = newQty))  // Update with new quantity
                }
                if(currentQty == 1) {
                    roomDBVM.removeItem(incartItem)
                }
            }

            constraintLayout.elevation = 50f
            val radius = 50f
            constraintLayout.outlineProvider = CustomOutlineProvider(radius)
        }
    }
}