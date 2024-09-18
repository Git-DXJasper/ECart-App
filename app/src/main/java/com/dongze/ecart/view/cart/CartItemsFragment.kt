package com.dongze.ecart.view.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
                rvItemsIncart.adapter = InCartItemAdapter(itemList, roomDBVM)
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