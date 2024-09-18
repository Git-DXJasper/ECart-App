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
import com.dongze.ecart.view.CustomOutlineProvider
import com.dongze.ecart.viewModel.LoginViewModel

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var loginFrag: LoginFragment
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
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
            btnSignup.elevation = 50f
            edtEmail.elevation = 50f
            edtName.elevation = 50f
            edtPhone.elevation = 50f
            edtPassword.elevation = 50f
            val radius = 50f
            btnSignup.outlineProvider = CustomOutlineProvider(radius)
            edtEmail.outlineProvider = CustomOutlineProvider(radius)
            edtName.outlineProvider = CustomOutlineProvider(radius)
            edtPhone.outlineProvider = CustomOutlineProvider(radius)
            edtPassword.outlineProvider = CustomOutlineProvider(radius)
        }
    }

    private fun navToFrag(frag: Fragment) =
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_login,frag)
            .commit()

}