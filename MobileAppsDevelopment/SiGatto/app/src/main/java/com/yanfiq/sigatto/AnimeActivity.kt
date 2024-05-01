package com.yanfiq.sigatto

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AnimeActivity : AppCompatActivity() {
    private lateinit var rvAnime: RecyclerView
    private val list = ArrayList<Anime>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_anime)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvAnime = findViewById(R.id.rvAnime)
        rvAnime.setHasFixedSize(true)

        list.addAll(getListAnime())
        showRecyclerList()
    }

    private fun getListAnime() : ArrayList<Anime> {
        val dataName = resources.getStringArray(R.array.data_anime_name)
        val dataImg = resources.obtainTypedArray(R.array.data_anime_img)
        val dataShortDesc = resources.getStringArray(R.array.data_anime_shortDesc)
        val dataLongDesc = resources.getStringArray(R.array.data_anime_longDesc)
        val listHero = ArrayList<Anime>()
        for (i in dataName.indices) {
            val hero = Anime(dataName[i], dataImg.getResourceId(i, -1), dataShortDesc[i], dataLongDesc[i])
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rvAnime.layoutManager = LinearLayoutManager(this)
        val listAnimeAdapter = ListAnimeAdapter(list)
        rvAnime.adapter = listAnimeAdapter
        listAnimeAdapter.setOnItemClickCallback(object : ListAnimeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Anime) {
                seeDetails(data)
            }
        })
    }

    private fun seeDetails(data: Anime){
        val intent = Intent(this, AnimeDetailsActivity::class.java)
        intent.putExtra("animeTitle", data.name)
        intent.putExtra("animePict", data.img)
        intent.putExtra("animeDesc", data.longDesc)
        startActivity(intent)
    }
}