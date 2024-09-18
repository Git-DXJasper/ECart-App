package com.dongze.ecart.view.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentOrderDetailBinding
import com.dongze.ecart.viewModel.AndroidViewModelFactory
import com.dongze.ecart.viewModel.OrderViewModel


class OrderDetailFragment : Fragment() {
    private lateinit var binding: FragmentOrderDetailBinding
    private var orderId: Int? = null
    private lateinit var viewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            AndroidViewModelFactory(requireActivity().application)
        ).get(OrderViewModel::class.java)
        orderId?.let {
            viewModel.getOrderDetail(it)
        }
        viewModel.getOrderDetailResponse.observe(viewLifecycleOwner){
            with(binding){
                txtOrderId.text = "Order ID: ${it.order.orderId}"
                txtAddrTitle.text = "Address Title: ${it.order.addressTitle}"
                txtAddr.text = "Address: ${it.order.address}"
                txtBillAmount.text = "Bill Amount: ${it.order.billAmount}"
                txtPayment.text = "Payment Method: ${it.order.paymentMethod}"
                txtOrderStatus.text = "Order Status: ${it.order.orderStatus}"
                txtOrderDate.text = "Order Date: ${it.order.orderDate}"
                rvItemsInOrderDetail.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                rvItemsInOrderDetail.adapter = ItemInOdAdapter(it.order.items)
            }
        }
    }

    fun receiveOrderId(orderId: Int){
        this.orderId = orderId
    }
}