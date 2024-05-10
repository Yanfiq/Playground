package com.yanfiq.sigatto.ui.hobby

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yanfiq.sigatto.R
import com.yanfiq.sigatto.SectionPagerAdapter
import androidx.core.content.ContextCompat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HobbyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HobbyFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewOfLayout: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)

        viewOfLayout = inflater!!.inflate(R.layout.fragment_hobby, container, false)
        val sectionsPagerAdapter = SectionPagerAdapter(requireActivity())
        val viewPager: ViewPager2 = viewOfLayout.findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = viewOfLayout.findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            // Inflate custom tab layout
            val customTabView = LayoutInflater.from(requireContext()).inflate(R.layout.hobby_tab_layout, null)

            // Get references to views in custom layout
            val tabIcon = customTabView.findViewById<ImageView>(R.id.tab_icon)
            val tabText = customTabView.findViewById<TextView>(R.id.tab_text)

            // Set icon and text for the tab
            tabIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), TAB_ICON[position]))
            tabText.text = resources.getString(TAB_TITLES[position])

            // Set custom view for the tab
            tab.customView = customTabView
        }.attach()

        (activity as AppCompatActivity).supportActionBar?.elevation = 0f
        // Inflate the layout for this fragment
        return viewOfLayout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HobbyFragment.
         */

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HobbyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_anime,
            R.string.tab_text_book
        )

        private val TAB_ICON = intArrayOf(
            R.drawable.tab_icon_anime,
            R.drawable.tab_icon_book
        )
    }
}