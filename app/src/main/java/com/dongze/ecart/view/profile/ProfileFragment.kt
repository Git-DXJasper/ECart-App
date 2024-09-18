package com.dongze.ecart.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentProfileBinding
import com.dongze.ecart.model.local.SecuredSPManager
import com.dongze.ecart.model.local.SecuredSPManager.KEY_USER
import com.dongze.ecart.view.cart.SummaryFragment


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var addrFrag: AddressFragment
    private lateinit var summaryFrag: SummaryFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addrFrag = AddressFragment()
        summaryFrag = SummaryFragment()

        with(binding){
            txtUsername.text = "Welcome, ${SecuredSPManager.getUser()?.name}!"

            btnAddr.setOnClickListener {
                navToFrag(addrFrag)
            }
            btnOrders.setOnClickListener {
                navToFrag(summaryFrag)
            }
        }
    }

    private fun navToFrag(frag: Fragment) =
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main,frag)
            .commit()
}