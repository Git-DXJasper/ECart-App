package com.dongze.ecart.view.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentSubCategoryBinding
import com.dongze.ecart.viewModel.CategoryViewModel
import com.google.android.material.tabs.TabLayoutMediator

class SubCategoryFragment : Fragment() {
    private lateinit var binding: FragmentSubCategoryBinding
    private lateinit var viewModel: CategoryViewModel
    private lateinit var cid: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this@SubCategoryFragment)[CategoryViewModel::class.java]
        viewModel.getSubCatList(cid)
        viewModel.getSubCatRes.observe(viewLifecycleOwner){
                response->
            Toast.makeText(requireContext(),response.message, Toast.LENGTH_SHORT).show()
            val subCatList = response.subcategories
            val subCatNameList = subCatList.map{ it.subCname }
            val fragList = subCatList.map{ subCat->
                ProductListFragment().apply {
                    arguments = Bundle().apply{
                        putString("subCid",subCat.subCid)
                    }
                }
            }
            binding.vp2.adapter = ViewPagerAdapter(fragList, requireActivity())

            binding.tabLayout.setTabTextColors(
                ContextCompat.getColor(requireContext(), R.color.white), // Unselected color
                ContextCompat.getColor(requireContext(), R.color.orange) // Selected color
            )

            TabLayoutMediator(binding.tabLayout, binding.vp2){ tab, position->
                tab.text = subCatNameList[position]
            }.attach()
        }
    }

    fun receiveCid(cid: String){
        this.cid = cid
    }
}