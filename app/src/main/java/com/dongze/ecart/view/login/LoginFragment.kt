package com.dongze.ecart.view.login

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentLoginBinding
import com.dongze.ecart.model.local.SecuredSPManager
import com.dongze.ecart.model.local.SecuredSPManager.KEY_IS_LOGIN
import com.dongze.ecart.view.MainActivity
import com.dongze.ecart.viewModel.LoginViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var registerFrag: RegisterFragment
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPreferenceExists()

        registerFrag = RegisterFragment()

        with(binding){
            btnToSignup.setOnClickListener { navToFrag(registerFrag) }
            btnLogin.setOnClickListener {
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                viewModel = ViewModelProvider(this@LoginFragment)[LoginViewModel::class.java]
                viewModel.login(email, password)
                viewModel.authUserRes.observe(viewLifecycleOwner){
                    response->
                    Toast.makeText(requireContext(),response.message,Toast.LENGTH_SHORT).show()
                    SecuredSPManager.saveBoolean(KEY_IS_LOGIN, true)
                    SecuredSPManager.saveUser(response.user)
                    navtoMainActivity()
                }
            }
        }
    }

    private fun checkPreferenceExists() {
        val isLogin = SecuredSPManager.getBoolean(KEY_IS_LOGIN, false)
        if(isLogin){
            navtoMainActivity()
        }
    }

    private fun navtoMainActivity() {
        val intent = Intent(requireContext(),MainActivity::class.java)
        startActivity(intent)
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