package com.dongze.ecart.view.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentProfileBinding
import com.dongze.ecart.model.local.SecuredSPManager
import com.dongze.ecart.model.local.SecuredSPManager.KEY_IS_LOGIN
import com.dongze.ecart.model.local.SecuredSPManager.KEY_USER
import com.dongze.ecart.view.CustomOutlineProvider
import com.dongze.ecart.view.LoginActivity
import com.dongze.ecart.view.cart.SummaryFragment
import com.dongze.ecart.viewModel.LoginViewModel


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var addrFrag: AddressFragment
    private lateinit var summaryFrag: SummaryFragment
    private lateinit var viewModel: LoginViewModel

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
        viewModel = ViewModelProvider(this@ProfileFragment)[LoginViewModel::class.java]

        with(binding){
            txtUsername.text = "Welcome, ${SecuredSPManager.getUser()?.name}!"

            btnAddr.setOnClickListener {
                navToFrag(addrFrag)
            }
            btnOrders.setOnClickListener {
                navToFrag(summaryFrag)
            }
            btnLogout.setOnClickListener {
                val emailId = SecuredSPManager.getUser()?.email
                viewModel.logout(emailId!!)
                viewModel.logoutRes.observe(viewLifecycleOwner){
                    response->
                    SecuredSPManager.clear()
                    val intent = Intent(requireContext(),LoginActivity::class.java)
                    startActivity(intent)
                }
            }

            btnAddr.elevation = 50f
            btnPayment.elevation = 50f
            btnOrders.elevation = 50f
            btnSettings.elevation = 50f
            btnLogout.elevation = 50f
            val radius = 50f
            btnAddr.outlineProvider = CustomOutlineProvider(radius)
            btnPayment.outlineProvider = CustomOutlineProvider(radius)
            btnOrders.outlineProvider = CustomOutlineProvider(radius)
            btnSettings.outlineProvider = CustomOutlineProvider(radius)
            btnLogout.outlineProvider = CustomOutlineProvider(radius)
        }
    }

    private fun navToFrag(frag: Fragment) =
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main,frag)
            .commit()
}