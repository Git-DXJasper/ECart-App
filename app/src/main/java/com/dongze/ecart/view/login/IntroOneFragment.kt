package com.dongze.ecart.view.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentIntroOneBinding
import com.dongze.ecart.model.local.SecuredSPManager
import com.dongze.ecart.model.local.SecuredSPManager.KEY_IS_LOGIN
import com.dongze.ecart.model.local.SecuredSPManager.KEY_ONE_DONE
import com.dongze.ecart.model.local.SecuredSPManager.KEY_THREE_DONE
import com.dongze.ecart.model.local.SecuredSPManager.KEY_TWO_DONE
import com.dongze.ecart.view.MainActivity

class IntroOneFragment : Fragment() {
    private lateinit var binding: FragmentIntroOneBinding
    private lateinit var loginFrag: LoginFragment
    private lateinit var intro2Frag: IntroTwoFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroOneBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginFrag = LoginFragment()
        intro2Frag = IntroTwoFragment()

        checkPreferenceExists()

        with(binding){
            btnNext.setOnClickListener {
                navToFrag(intro2Frag)
                SecuredSPManager.saveBoolean(KEY_ONE_DONE, true)
            }
            btnSkip.setOnClickListener {
                navToFrag(loginFrag)
                SecuredSPManager.saveBoolean(KEY_ONE_DONE, true)
                SecuredSPManager.saveBoolean(KEY_TWO_DONE, true)
                SecuredSPManager.saveBoolean(KEY_THREE_DONE, true)
            }
        }
    }

    private fun checkPreferenceExists() {
        val isLogin = SecuredSPManager.getBoolean(KEY_IS_LOGIN, false)
        if(isLogin){
            navtoMainActivity()
        }
        val isOneDone = SecuredSPManager.getBoolean(KEY_ONE_DONE,false)
        if(isOneDone){
            navToFrag(intro2Frag)
        }
    }

    private fun navtoMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }

    private fun navToFrag(frag: Fragment) =
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_login,frag)
            .commit()

}