package com.yanfiq.sigatto

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnProfile : Button
    private lateinit var btnGithub : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnProfile = findViewById(R.id.btnProfile)
        btnGithub = findViewById(R.id.btnGithub)

        btnProfile.setOnClickListener(this)
        btnGithub.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnProfile -> {
                val profileIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(profileIntent)
            }
            R.id.btnGithub -> {
                val implicitIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Yanfiq"))
                startActivity(implicitIntent)
            }
        }
    }
}