package com.dongze.ecart.view.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentDashboardBinding
import com.dongze.ecart.view.Communicator
import com.dongze.ecart.viewModel.SearchViewModel

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SearchViewModel
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        communicator = requireActivity() as Communicator

        viewModel = ViewModelProvider(this@DashboardFragment)[SearchViewModel::class.java]
        with(binding){
            btnSearch.setOnClickListener{
                val keyword = edtKeyword.text.toString()
                viewModel.searchProduct(keyword)
                viewModel.searchRes.observe(viewLifecycleOwner){
                    response->
                    Toast.makeText(requireContext(),response.message, Toast.LENGTH_SHORT).show()
                    communicator.sendPList(response.products)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}