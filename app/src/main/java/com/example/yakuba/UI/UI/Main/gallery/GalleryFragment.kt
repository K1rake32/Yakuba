package com.example.yakuba.UI.UI.Main.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yakuba.R
import com.example.yakuba.Recycle.App
import com.example.yakuba.Recycle.gallery.GalleryAdapter
import com.example.yakuba.Recycle.gallery.GalleryRecycle
import com.example.yakuba.Recycle.gallery.ItemOffsetDecoration
import com.example.yakuba.Recycle.home.PostAdapter
import com.example.yakuba.Recycle.home.PostRecycle
import com.example.yakuba.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private lateinit var adapter: GalleryAdapter
    private val galleryRecycle: GalleryRecycle
        get() = (requireActivity().application as App).galleryService


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcGallery()
    }

    private fun rcGallery() {

        val manager = GridLayoutManager(requireContext(), 1)

        adapter = GalleryAdapter()

        adapter.data = galleryRecycle.getGallery()


        with(binding) {
            rcViewGallery.layoutManager = manager
            rcViewGallery.adapter = adapter

        }

    }


}