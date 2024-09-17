package com.dongze.ecart.view.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentCategoryBinding
import com.dongze.ecart.view.Communicator
import com.dongze.ecart.viewModel.CategoryViewModel

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding

    private lateinit var viewModel: CategoryViewModel
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        communicator = requireActivity() as Communicator

        viewModel = ViewModelProvider(this@CategoryFragment)[CategoryViewModel::class.java]
        viewModel.getCatList()
        viewModel.getCatRes.observe(viewLifecycleOwner){
            response->
            Toast.makeText(requireContext(),response.message, Toast.LENGTH_SHORT).show()
            binding.rvCat.layoutManager = GridLayoutManager(requireContext(),2)
            binding.rvCat.adapter = CatAdapter(response.categories, communicator)
        }
    }
}