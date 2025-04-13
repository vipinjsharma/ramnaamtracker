package com.ramlekhak.ui.profile

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ramlekhak.R
import com.ramlekhak.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupProfileHeader()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Profile"
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setupProfileHeader() {
        // Set initial values
        binding.profileHeader.setName("Ram Bhakt")
        binding.profileHeader.setMemberSince("March 2025")
        
        // Set listener for name changes
        binding.profileHeader.setOnNameChangedListener { newName ->
            // In a real app, save this to preferences or backend
            Toast.makeText(this, "Updated name to: $newName", Toast.LENGTH_SHORT).show()
        }
    }
} 