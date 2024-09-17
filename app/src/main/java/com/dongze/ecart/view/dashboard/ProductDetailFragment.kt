package com.dongze.ecart.view.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentProductDetailBinding
import com.dongze.ecart.model.remote.dashboard.ProductDetail
import com.dongze.ecart.view.cart.CartFragment
import com.dongze.ecart.viewModel.ProductDetailViewModel
import com.dongze.ecart.viewModel.RoomDBViewModel
import com.squareup.picasso.Picasso

class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var pid : String
    private lateinit var viewModel: ProductDetailViewModel
    private lateinit var roomDBVM: RoomDBViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ProductDetailViewModel::class.java]
        roomDBVM = ViewModelProvider(requireActivity())[RoomDBViewModel::class.java]
        viewModel.getDetail(pid)
        viewModel.pDetailRes.observe(viewLifecycleOwner){
            response->
            Toast.makeText(requireContext(),response.message, Toast.LENGTH_SHORT).show()
            val p = response.product //pDetail actually
            with(binding){
                txtPid.text = "[${p.pid}]"
                txtPname.text = p.pname
                txtDescription.text = p.description
                txtPrice.text = "$${p.price}"
                Picasso.get().load("https://apolisrises.co.in/myshop/images/" + p.pImgUrl).into(imgProduct)
                btnAddToCart.setOnClickListener {
                    roomDBVM.sendPDetail(p)
                    navToFrag(CartFragment())
                }
            }
        }
    }

    private fun navToFrag(frag: Fragment) =
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main,frag)
            .commit()

    fun receivePid(pid: String){
        this.pid = pid
    }
}