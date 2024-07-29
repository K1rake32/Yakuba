package com.example.yakuba.UI.UI.Main.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yakuba.Recycle.App
import com.example.yakuba.Recycle.addPost.AddPost
import com.example.yakuba.Recycle.addPost.AddPostAdapter
import com.example.yakuba.Recycle.addPost.AddPostRecycle
import com.example.yakuba.databinding.FragmentAddPostPhotoBinding

class AddPostPhotoFragment : Fragment() {

    private lateinit var binding: FragmentAddPostPhotoBinding
    private lateinit var adapter: AddPostAdapter

    private val addPostRecycle: AddPostRecycle
        get() = (requireActivity().application as App).addPostService

    private val selectImagesLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val selectedPosts = mutableListOf<AddPost>()

            data?.let {
                val clipData = it.clipData
                if (clipData != null) {
                    for (i in 0 until clipData.itemCount) {
                        val uri: Uri = clipData.getItemAt(i).uri
                        selectedPosts.add(AddPost(uri.toString()))
                    }
                } else {
                    val uri: Uri? = it.data
                    uri?.let {
                        selectedPosts.add(AddPost(it.toString()))
                    }
                }
            }
            adapter.addPosts(selectedPosts)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPostPhotoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcAddPost()
    }

    private fun rcAddPost() {
        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        adapter = AddPostAdapter(selectImagesLauncher)
        adapter.data = addPostRecycle.getAddPost()

        with(binding) {
            rcAddPost.layoutManager = manager
            rcAddPost.adapter = adapter
        }
    }
}