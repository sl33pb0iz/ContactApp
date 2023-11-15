package com.example.contactapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val backButton: ImageView = findViewById(R.id.back_button)
        val avatarTextView: TextView = findViewById(R.id.avatar)
        val nameTextView: TextView = findViewById(R.id.name)
        val phoneTextView: TextView = findViewById(R.id.phone)
        val emailTextView: TextView = findViewById(R.id.email)

        backButton.setOnClickListener {
            finish()
        }

        avatarTextView.text = intent.getStringExtra("NAME")?.first().toString()
        nameTextView.text = intent.getStringExtra("NAME")
        phoneTextView.text = intent.getStringExtra("PHONE")
        emailTextView.text = intent.getStringExtra("EMAIL")
    }
}
