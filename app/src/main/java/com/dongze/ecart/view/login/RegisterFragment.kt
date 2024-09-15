package com.dongze.ecart.view.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentRegisterBinding
import com.dongze.ecart.viewModel.LoginViewModel

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var loginFrag: LoginFragment
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginFrag = LoginFragment()

        with(binding){
            btnSignup.setOnClickListener {
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                val name = edtName.text.toString()
                val phone = edtPhone.text.toString()
                viewModel = ViewModelProvider(this@RegisterFragment)[LoginViewModel::class.java]
                viewModel.signup(email, password, name, phone)
                viewModel.registerRes.observe(viewLifecycleOwner){
                    response->
                    Toast.makeText(requireContext(),response.message, Toast.LENGTH_SHORT).show()
                    navToFrag(loginFrag)
                }
            }
        }
    }

    private fun navToFrag(frag: Fragment) =
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_login,frag)
            .commit()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}