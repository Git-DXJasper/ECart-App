package com.dongze.ecart.view.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentDeliveryBinding
import com.dongze.ecart.model.local.SecuredSPManager
import com.dongze.ecart.model.local.SecuredSPManager.KEY_DELIVERY
import com.dongze.ecart.viewModel.AddressViewModel

class DeliveryFragment : Fragment() {
    private lateinit var binding: FragmentDeliveryBinding
    private lateinit var viewModel: AddressViewModel
    private var addrOptions: List<String> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeliveryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this@DeliveryFragment)[AddressViewModel::class.java]
        viewModel.getAddrList()
        viewModel.getAddrListRes.observe(viewLifecycleOwner){
            response->
            addrOptions = response.addresses.map { "[${it.title}]: ${it.address}" }
            createRG()
        }
    }

    private fun createRG(){
        for((index,option) in addrOptions.withIndex()){
            val rb = RadioButton(requireContext()).apply {
                text = option
                id = index
            }
            binding.rgAddrOptions.addView(rb)
        }

        binding.rgAddrOptions.setOnCheckedChangeListener { radioGroup, checkedId ->
            val selectedOption = addrOptions[checkedId]
            SecuredSPManager.saveString(KEY_DELIVERY,selectedOption)

            //when retrieving
            //val title = sp.substringAfter("[").substringBefore("]")
            //val addr = sp.substringAfter(": ").trim()
        }
    }
}