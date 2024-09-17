package com.dongze.ecart.view.profile

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dongze.ecart.R
import com.dongze.ecart.databinding.DialogAddAddrBinding
import com.dongze.ecart.databinding.FragmentAddressBinding
import com.dongze.ecart.model.local.SecuredSPManager
import com.dongze.ecart.viewModel.AddressViewModel

class AddressFragment : Fragment() {
    private lateinit var binding: FragmentAddressBinding
    private lateinit var viewModel: AddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =ViewModelProvider(this@AddressFragment)[AddressViewModel::class.java]
        binding.fabAdd.setOnClickListener {
            showAddAddrDialog()
        }
        viewModel.getAddrList()
        viewModel.getAddrListRes.observe(viewLifecycleOwner){
            response->
            binding.rvAddrlist.layoutManager = LinearLayoutManager(requireContext())
            binding.rvAddrlist.adapter = AddrAdapter(response.addresses)
        }
    }

    private fun showAddAddrDialog() {
        val dialogBinding = DialogAddAddrBinding.inflate(LayoutInflater.from(requireContext()))
        val builder = AlertDialog.Builder(requireContext()).setView(dialogBinding.root)
        val dialog = builder.create()
        dialog.window?.setGravity(Gravity.TOP)

        with(dialogBinding){
            btnConfirm.setOnClickListener {
                val newAddr = edtAddr.text.toString()
                val newTitle = edtTitle.text.toString()
                viewModel.addAddr(SecuredSPManager.getUser()?.userId?:-1,newTitle,newAddr)
                viewModel.addAddrRes.observe(viewLifecycleOwner){
                    response->
                    Toast.makeText(requireContext(),response.message, Toast.LENGTH_SHORT).show()
                    viewModel.getAddrList()
                    dialog.dismiss()
                }
            }
        }
        dialog.show()
    }

}