package com.dongze.ecart.view.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentProductListBinding
import com.dongze.ecart.model.remote.category.Product
import com.dongze.ecart.model.remote.dashboard.ProductDetail
import com.dongze.ecart.view.Communicator
import com.dongze.ecart.viewModel.CategoryViewModel


class ProductListFragment : Fragment() {
    private var subCid: String? = null
    private lateinit var binding: FragmentProductListBinding
    private lateinit var viewModel: CategoryViewModel
    private lateinit var plist: List<ProductDetail>
    private lateinit var communicator: Communicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            subCid = it.getString("subCid")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        communicator = requireActivity() as Communicator

        if(::plist.isInitialized){
            binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
            val products = ArrayList<Product>()//converting list of pDetail into list of product
            for(p in plist){
                products.add(Product(p.averageRating,p.cid,"",p.description,p.price,
                    p.pid,p.pImgUrl,p.pname,p.subCid,""))
            }

            binding.rvProducts.adapter = ProductAdapter(products,communicator)
            return
        }

        viewModel = ViewModelProvider(this@ProductListFragment)[CategoryViewModel::class.java]
        subCid?.let {
            viewModel.getPList(it)
        }
        viewModel.getPListRes.observe(viewLifecycleOwner){
            response->
            Toast.makeText(requireContext(),response.message, Toast.LENGTH_SHORT).show()
            binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
            binding.rvProducts.adapter = ProductAdapter(response.products,communicator)
        }
    }

    fun receivePList(plist: List<ProductDetail>){
        this.plist = plist
    }
}