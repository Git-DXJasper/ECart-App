package com.dongze.ecart.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dongze.ecart.R
import com.dongze.ecart.databinding.ActivityLoginBinding
import com.dongze.ecart.view.login.IntroOneFragment

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navToIntroOneFrag()
    }

    private fun navToIntroOneFrag() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_login,IntroOneFragment())
            .commit()
    }
}