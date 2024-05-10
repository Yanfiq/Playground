package com.yanfiq.sigatto.ui.hobby

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yanfiq.sigatto.R
import com.yanfiq.sigatto.databinding.FragmentHobbyAnimeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Book_HobbyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Book_HobbyFragment : Fragment() {
    private var _binding: FragmentHobbyAnimeBinding? = null
    private lateinit var rvBook: RecyclerView
    private val list = ArrayList<Book>()
    private lateinit var viewOfLayout: View

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHobbyAnimeBinding.inflate(inflater, container, false)

        viewOfLayout = inflater!!.inflate(R.layout.fragment_hobby_book, container, false)
        rvBook = viewOfLayout.findViewById(R.id.rvBook)
        rvBook.setHasFixedSize(true)

        list.addAll(getListBook())
        showRecyclerList()
        return viewOfLayout
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getListBook() : ArrayList<Book> {
        val dataName = resources.getStringArray(R.array.data_book_name)
        val dataImg = resources.obtainTypedArray(R.array.data_book_img)
        val dataDesc = resources.getStringArray(R.array.data_book_desc)
        val listHero = ArrayList<Book>()
        for (i in dataName.indices) {
            val hero = Book(dataName[i], dataImg.getResourceId(i, -1), dataDesc[i])
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rvBook.layoutManager = LinearLayoutManager(context)
        val listBookAdapter = ListBookAdapter(list)
        rvBook.adapter = listBookAdapter
        listBookAdapter.setOnItemClickCallback(object : ListBookAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Book) {
//                seeDetails(data)
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Book_HobbyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Book_HobbyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}