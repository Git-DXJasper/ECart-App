package com.dongze.ecart.view.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dongze.ecart.databinding.FragmentCartItemsBinding
import com.dongze.ecart.model.local.InCartItem
import com.dongze.ecart.model.local.SecuredSPManager
import com.dongze.ecart.viewModel.RoomDBViewModel
import com.dongze.ecart.viewModel.RoomDBViewModelFactory

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

        roomDBVM = ViewModelProvider(requireActivity(), RoomDBViewModelFactory(requireActivity().application))
            .get(RoomDBViewModel::class.java)

        roomDBVM.pDetailLiveData.observe(viewLifecycleOwner){
            Log.d("RoomDB","cartItemFrag observing $it")
            roomDBVM.storeInCartItem(it)
        }
        roomDBVM.getListFromRoom(SecuredSPManager.getUser()?.userId?:-1)
        roomDBVM.inCartItemList.observe(viewLifecycleOwner) {
            binding.rvItemsIncart.layoutManager = LinearLayoutManager(requireContext())
            binding.rvItemsIncart.adapter = InCartItemAdapter(it)
        }
    }

}