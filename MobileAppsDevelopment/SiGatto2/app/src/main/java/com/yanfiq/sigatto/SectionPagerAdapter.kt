package com.yanfiq.sigatto

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yanfiq.sigatto.ui.hobby.Anime_HobbyFragment
import com.yanfiq.sigatto.ui.hobby.Book_HobbyFragment

class SectionPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = Anime_HobbyFragment()
            1 -> fragment = Book_HobbyFragment()
        }
        return fragment as Fragment
    }
}