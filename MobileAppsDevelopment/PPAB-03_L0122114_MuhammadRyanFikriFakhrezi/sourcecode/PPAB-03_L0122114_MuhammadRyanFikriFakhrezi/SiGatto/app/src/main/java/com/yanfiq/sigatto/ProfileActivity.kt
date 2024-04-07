package com.yanfiq.sigatto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnShare : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnShare = findViewById(R.id.btnShare)
        btnShare.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnShare -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, """
                        NIM     : L0122114
                        Name    : Muhammad Ryan Fikri Fakhrezi
                        Major   : Informatics
                        Gen     : 2022
                        Desc    : Introducing my name is Muhammad Ryan Fikri Fakhrezi, you can call me Ryan. I come from Wonogiri and am currently studying computer science at Sebelas Maret University Surakarta. My favorite programming language is Java because I come from Java.
                    """.trimIndent())
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }

}