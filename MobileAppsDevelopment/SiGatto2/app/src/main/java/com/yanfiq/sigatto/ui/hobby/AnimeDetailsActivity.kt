package com.yanfiq.sigatto.ui.hobby

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yanfiq.sigatto.R

class AnimeDetailsActivity : AppCompatActivity() {

    private lateinit var animeTitle : TextView
    private lateinit var animePicture : ImageView
    private lateinit var animeDesc : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_anime_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        animeTitle = findViewById(R.id.animeTitle)
        animePicture = findViewById(R.id.animePicture)
        animeDesc = findViewById(R.id.animeDescription)

        animeTitle.text = intent.getStringExtra("animeTitle")
        animePicture.setImageResource(intent.getIntExtra("animePict", -1))
        animeDesc.text = intent.getStringExtra("animeDesc")
    }
}