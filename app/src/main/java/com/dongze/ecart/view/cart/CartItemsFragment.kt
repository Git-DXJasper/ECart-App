package com.dongze.ecart.view.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dongze.ecart.databinding.FragmentCartItemsBinding
import com.dongze.ecart.model.local.SecuredSPManager
import com.dongze.ecart.viewModel.RoomDBViewModel
import com.dongze.ecart.viewModel.AndroidViewModelFactory

class CartItemsFragment : Fragment() {
    private lateinit var binding: FragmentCartItemsBinding
    private lateinit var roomDBVM: RoomDBViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartItemsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        roomDBVM = ViewModelProvider(
            requireActivity(),
            AndroidViewModelFactory(requireActivity().application)
        ).get(RoomDBViewModel::class.java)

        roomDBVM.pDetailLiveData.observe(viewLifecycleOwner) {
            Log.d("RoomDB", "cartItemFrag observing $it")
            roomDBVM.storeInCartItem(it)
        }
        roomDBVM.getListFromRoom(SecuredSPManager.getUser()?.userId ?: -1)
        roomDBVM.inCartItemList.observe(viewLifecycleOwner) { itemList->
            with(binding) {
                rvItemsIncart.layoutManager = LinearLayoutManager(requireContext())
                val inCartItemAdapter = InCartItemAdapter(itemList, roomDBVM)

                ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
                    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder)=false

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val position = viewHolder.adapterPosition
                        roomDBVM.removeItem(itemList[position])
                        inCartItemAdapter.notifyItemRemoved(position)
                        Toast.makeText(requireContext(),"Item removed from cart!",Toast.LENGTH_SHORT).show()
                    }

                }).attachToRecyclerView(rvItemsIncart)
                rvItemsIncart.adapter = inCartItemAdapter
            }
        }
        binding.btnCheckout.setOnClickListener {
            reCalculateTotalPrice()
        }
    }

    private fun reCalculateTotalPrice() {
        roomDBVM.getListFromRoom(SecuredSPManager.getUser()?.userId ?: -1)
        roomDBVM.inCartItemList.observe(viewLifecycleOwner) { itemList->
            with(binding) {
                val totalPrice = itemList.sumOf { it.price.toDouble() * it.qty }
                txtTotalPrice.text = "Total price is $$totalPrice"
            }
        }
    }
}