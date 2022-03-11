package com.example.edvora.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.edvora.fragment.TabFragment

internal class ViewPagerAdapter(manager: FragmentManager?) :
    FragmentPagerAdapter(manager!!) {
    private val title = arrayOf("One", "Two", "Three")
    override fun getItem(position: Int): Fragment {
        return TabFragment.getInstance(position)
    }

    override fun getCount(): Int {
        return title.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }
}


