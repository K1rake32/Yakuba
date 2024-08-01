package com.example.yakuba.UI.UI.Main.home.story

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class StoryAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = listOf(
        FragmentStoryOne(),
        FragmentStoryTwo(),
        FragmentStoryThree()
    )
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int = fragmentList.size

}

