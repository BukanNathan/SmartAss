package com.example.pethub_pembayaran_new

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    var pages = listOf(HistoryFragment1(),HistoryFragment2(),HistoryFragment3())
    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }
}