package com.example.loginapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>("user_data")
        val dari = intent.getStringExtra("source")

        val welcomeMsg = if (dari == "register") {
            "Welcome, ${user?.name ?: "User"}!"
        } else {
            "Welcome, ${user?.email ?: "User"}!"
        }

        binding.tvWelcome.text = welcomeMsg
    }
}
