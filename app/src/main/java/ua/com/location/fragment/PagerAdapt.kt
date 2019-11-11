package ua.com.location.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapt( fa: FragmentManager): FragmentStatePagerAdapter(fa,
    FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mFragment: ArrayList<Fragment> = ArrayList()
    private val mTitle: ArrayList<String> = ArrayList()

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {

        when (position) {
            0 -> mFragment.add(position, ListAndTrack())
            1 -> mFragment.add(position, Map())
        }
        return mFragment[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> mTitle.add(position, "List" )
            1 -> mTitle.add(position, "Map")
        }
        return mTitle[position]

    }
}