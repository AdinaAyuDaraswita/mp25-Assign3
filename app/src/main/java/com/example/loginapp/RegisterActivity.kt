package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            handleRegister()
        }
    }

    private fun handleRegister() {
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val pass = binding.etPassword.text.toString()

        if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            showSnackbar("Semua field harus diisi!")
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showSnackbar("Email tidak valid!")
            return
        }

        val userBaru = User(name, email, pass)
        val goLanding = Intent(this, LandingActivity::class.java)
        goLanding.putExtra("user_data", userBaru)
        goLanding.putExtra("source", "register")
        startActivity(goLanding)
        finish()
    }

    private fun showSnackbar(pesan: String) {
        Snackbar.make(binding.registerLayout, pesan, Snackbar.LENGTH_LONG).show()
    }
}
