package com.example.yakuba.UI.UI.Main.home

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yakuba.MAIN
import com.example.yakuba.NavigationFragment
import com.example.yakuba.R
import com.example.yakuba.Recycle.App
import com.example.yakuba.Recycle.coment.PersonAdapter
import com.example.yakuba.Recycle.coment.PersonRecycle
import com.example.yakuba.Recycle.home.Post
import com.example.yakuba.Recycle.home.PostAdapter
import com.example.yakuba.Recycle.home.PostRecycle
import com.example.yakuba.ViewPagerAdapter
import com.example.yakuba.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var adapter: PostAdapter
    private val postRecycle: PostRecycle
        get() = (requireActivity().application as App).postService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcPost()
        createPost()
        navigateStory()
    }

    private fun rcPost() {
        val manager = LinearLayoutManager(requireContext())
        adapter = PostAdapter()
        adapter.data = postRecycle.getPost()

        with(binding) {
            rcPost.layoutManager = manager
            rcPost.adapter = adapter
        }
    }

    private fun createPost() {
        with(binding) {
            addPost.setOnClickListener() {
                NavigationFragment.NavigationCreatePost(MAIN.navController)
            }
        }
    }

    private fun navigateStory() {
        binding.mainStory.setOnClickListener() {
            NavigationFragment.NavigationStory(MAIN.navController)
        }
    }

}

