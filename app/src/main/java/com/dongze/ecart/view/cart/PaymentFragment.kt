package com.dongze.ecart.view.cart

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentPaymentBinding
import com.dongze.ecart.model.local.SecuredSPManager
import com.dongze.ecart.model.local.SecuredSPManager.KEY_DELIVERY
import com.dongze.ecart.view.Communicator
import com.dongze.ecart.viewModel.AndroidViewModelFactory
import com.dongze.ecart.viewModel.OrderViewModel

class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding
    private lateinit var viewModel: OrderViewModel
    private lateinit var communicator: Communicator

    private val paymentOptions = listOf(
        "Credit Card","Debit Card","Bank Transfer","Digital Wallets","Cash on Delivery(COD)"
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communicator = requireActivity() as Communicator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            AndroidViewModelFactory(requireActivity().application)
        ).get(OrderViewModel::class.java)

        setUpRG()
        binding.btnPlaceOrder.setOnClickListener {
            viewModel.placeOrder()
            viewModel.placeOrderRes.observe(viewLifecycleOwner){
                Log.d("api_success", "place order success, ${it.message}, order id is: ${it.orderId}")
                Toast.makeText(requireContext(),"place order success, id:${it.orderId}",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setUpRG() {
        for((index, option) in paymentOptions.withIndex()){
            val rb = RadioButton(requireContext()).apply {
                text = option
                id = index
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            }
            val params = RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 54, 0, 54) // Add top and bottom margin of 54px
            }

            binding.rgPaymentOptions.addView(rb,params)
        }
        binding.rgPaymentOptions.check(0)

        binding.rgPaymentOptions.setOnCheckedChangeListener { radioGroup, checkedId ->
            val selectedOption = paymentOptions[checkedId]
            SecuredSPManager.saveString(KEY_DELIVERY,selectedOption)
        }
    }

}