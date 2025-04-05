package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            handleLogin()
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun handleLogin() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString()

        if (email.isBlank() || password.isBlank()) {
            showSnackbar("Email dan Password tidak boleh kosong!")
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showSnackbar("Format email tidak valid!")
            return
        }

        val userLogin = User("Guest", email, password)

        val goLanding = Intent(this, LandingActivity::class.java)
        goLanding.putExtra("user_data", userLogin)
        goLanding.putExtra("source", "login")
        startActivity(goLanding)
        finish()
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(binding.loginLayout, msg, Snackbar.LENGTH_LONG).show()
    }
}
