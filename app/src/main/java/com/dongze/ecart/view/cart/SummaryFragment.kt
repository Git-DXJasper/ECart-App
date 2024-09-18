package com.dongze.ecart.view.cart

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentSummaryBinding
import com.dongze.ecart.model.local.SecuredSPManager
import com.dongze.ecart.view.Communicator
import com.dongze.ecart.viewModel.AndroidViewModelFactory
import com.dongze.ecart.viewModel.OrderViewModel


class SummaryFragment : Fragment() {
    private lateinit var binding: FragmentSummaryBinding
    private lateinit var viewModel: OrderViewModel
    private lateinit var communicator: Communicator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communicator = requireActivity() as Communicator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSummaryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            AndroidViewModelFactory(requireActivity().application)
        ).get(OrderViewModel::class.java)

        viewModel.fetchOrderList(SecuredSPManager.getUser()?.userId?:-1)
        viewModel.fetchOrderListRes.observe(viewLifecycleOwner){
            binding.rvOrderList.layoutManager = LinearLayoutManager(requireContext())
            binding.rvOrderList.adapter = OrderAdapter(it.orders,communicator)
        }
    }

}