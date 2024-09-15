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
import com.dongze.ecart.viewModel.ProductDetailViewModel
import com.squareup.picasso.Picasso

class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var pid : String
    private lateinit var viewModel: ProductDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this@ProductDetailFragment)[ProductDetailViewModel::class.java]
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
            }
        }
    }

    fun receivePid(pid: String){
        this.pid = pid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}