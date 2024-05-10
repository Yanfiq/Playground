package com.yanfiq.sigatto.ui.hobby

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yanfiq.sigatto.R
import com.yanfiq.sigatto.databinding.FragmentHobbyAnimeBinding

class Anime_HobbyFragment : Fragment() {

    private var _binding: FragmentHobbyAnimeBinding? = null
    private lateinit var rvAnime: RecyclerView
    private val list = ArrayList<Anime>()
    private lateinit var viewOfLayout: View

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHobbyAnimeBinding.inflate(inflater, container, false)

        viewOfLayout = inflater!!.inflate(R.layout.fragment_hobby_anime, container, false)
        rvAnime = viewOfLayout.findViewById(R.id.rvAnime)
        rvAnime.setHasFixedSize(true)

        list.addAll(getListAnime())
        showRecyclerList()
        return viewOfLayout
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        rvAnime.layoutManager = LinearLayoutManager(context)
        val listAnimeAdapter = ListAnimeAdapter(list)
        rvAnime.adapter = listAnimeAdapter
        listAnimeAdapter.setOnItemClickCallback(object : ListAnimeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Anime) {
                seeDetails(data)
            }
        })
    }

    private fun seeDetails(data: Anime){
        val intent = Intent(requireActivity(), AnimeDetailsActivity::class.java)
        intent.putExtra("animeTitle", data.name)
        intent.putExtra("animePict", data.img)
        intent.putExtra("animeDesc", data.longDesc)
        startActivity(intent)
    }
}