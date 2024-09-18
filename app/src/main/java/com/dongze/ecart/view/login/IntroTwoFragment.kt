package com.dongze.ecart.view.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dongze.ecart.R
import com.dongze.ecart.databinding.FragmentIntroTwoBinding
import com.dongze.ecart.model.local.SecuredSPManager
import com.dongze.ecart.model.local.SecuredSPManager.KEY_IS_LOGIN
import com.dongze.ecart.model.local.SecuredSPManager.KEY_ONE_DONE
import com.dongze.ecart.model.local.SecuredSPManager.KEY_THREE_DONE
import com.dongze.ecart.model.local.SecuredSPManager.KEY_TWO_DONE
import com.dongze.ecart.view.CustomOutlineProvider
import com.dongze.ecart.view.MainActivity

class IntroTwoFragment : Fragment() {
    private lateinit var binding: FragmentIntroTwoBinding
    private lateinit var intro3Frag: IntroThreeFragment
    private lateinit var loginFrag: LoginFragment
    private lateinit var intro1Frag: IntroOneFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroTwoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intro3Frag = IntroThreeFragment()
        loginFrag = LoginFragment()
        intro1Frag = IntroOneFragment()
        checkPreferenceExists()

        with(binding){
            btnNext.setOnClickListener {
                navToFrag(intro3Frag)
                SecuredSPManager.saveBoolean(KEY_TWO_DONE, true)
            }
            btnNext.elevation = 40f
            val radius = 50f
            btnNext.outlineProvider = CustomOutlineProvider(radius)

            btnSkip.setOnClickListener {
                navToFrag(loginFrag)
                SecuredSPManager.saveBoolean(KEY_ONE_DONE, true)
                SecuredSPManager.saveBoolean(KEY_TWO_DONE, true)
                SecuredSPManager.saveBoolean(KEY_THREE_DONE, true)
            }
            btnBack.setOnClickListener { navToFrag(intro1Frag) }
        }
    }

    private fun checkPreferenceExists() {
        val isLogin = SecuredSPManager.getBoolean(KEY_IS_LOGIN, false)
        if(isLogin){
            navtoMainActivity()
        }
        val isTwoDone = SecuredSPManager.getBoolean(KEY_TWO_DONE,false)
        if(isTwoDone){
            navToFrag(intro3Frag)
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