package com.dongze.ecart.view.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentCartBinding
import com.google.android.material.tabs.TabLayoutMediator


class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragList = listOf<Fragment>(CartItemsFragment(),DeliveryFragment(),PaymentFragment(),SummaryFragment())
        binding.vp2.adapter = CheckOutVPAdapter(fragList, requireActivity())
        binding.tabLayout.setTabTextColors(
            ContextCompat.getColor(requireContext(), android.R.color.white), // Unselected color
            ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark) // Selected color
        )

        TabLayoutMediator(binding.tabLayout, binding.vp2){ tab, position->
            when(position){
                0->tab.text = "Cart"
                1->tab.text = "Delivery"
                2->tab.text = "Payment"
                3->tab.text = "Summary"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}