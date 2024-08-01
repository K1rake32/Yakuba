package com.example.yakuba.UI.UI.Main.home.story

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.yakuba.MAIN
import com.example.yakuba.NavigationFragment
import com.example.yakuba.databinding.FragmentMainStoryBinding

class MainStoryFragment : Fragment() {

    private lateinit var binding: FragmentMainStoryBinding
    private lateinit var dotsAdapter: DotsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainStoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerUI()
    }

    private fun pagerUI() {

        with(binding) {
            storyPager.adapter = StoryAdapter(requireActivity())
            val itemCount = (storyPager.adapter as StoryAdapter).itemCount

            dotsAdapter = DotsAdapter(requireContext(), itemCount)
            dotsRecycle.adapter = dotsAdapter
            dotsRecycle.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            storyPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    dotsAdapter.setSelectedPosition(position)
                }
            })

            closeStory.setOnClickListener() {
                NavigationFragment.NavigationStoryBack(MAIN.navController)
            }

        }
    }
}