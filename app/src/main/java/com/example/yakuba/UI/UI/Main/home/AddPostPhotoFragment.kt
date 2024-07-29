package com.example.yakuba.UI.UI.Main.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.yakuba.R
import com.example.yakuba.UI.UI.Auth.UserInformationFragment
import com.example.yakuba.databinding.FragmentAddPostPhotoBinding
import com.example.yakuba.databinding.FragmentGalleryBinding

class AddPostPhotoFragment : Fragment() {

    private lateinit var binding: FragmentAddPostPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPostPhotoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UserInformationFragment.GALLERY_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK && data != null ) {
            val selectedImageUri= data.data
            if(selectedImageUri != null ) {
                if (selectedImageUri != null )
                    binding.img.setImageResource(selectedImageUri)
            }
        }
    }

    companion object {
        private const val GALLERY_REQUEST_CODE = 100
    }

}