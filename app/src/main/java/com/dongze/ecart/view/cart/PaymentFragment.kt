package com.dongze.ecart.view.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentPaymentBinding
import com.dongze.ecart.model.local.SecuredSPManager
import com.dongze.ecart.model.local.SecuredSPManager.KEY_DELIVERY

class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding
    private val paymentOptions = listOf(
        "Credit Card","Debit Card","Bank Transfer","Digital Wallets","Cash on Delivery(COD)"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRG()
    }

    private fun setUpRG() {
        for((index, option) in paymentOptions.withIndex()){
            val rb = RadioButton(requireContext()).apply {
                text = option
                id = index
            }
            binding.rgPaymentOptions.addView(rb)
        }

        binding.rgPaymentOptions.setOnCheckedChangeListener { radioGroup, checkedId ->
            val selectedOption = paymentOptions[checkedId]
            SecuredSPManager.saveString(KEY_DELIVERY,selectedOption)
        }
    }

}