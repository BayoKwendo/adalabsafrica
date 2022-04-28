package com.asses.adian

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asses.adian.databinding.ActivityHomepageBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomepageBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.interfaceOne.setOnClickListener {
                    startActivity(Intent(this, WelcomeAc::class.java))
        finish()
        }
        binding.interfaceTwo.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.interfaceThree.setOnClickListener {
            startActivity(Intent(this, DepositActivity::class.java))
            finish()
        }

    }
}