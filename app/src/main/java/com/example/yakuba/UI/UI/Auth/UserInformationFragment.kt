package com.example.yakuba.UI.UI.Auth

import android.app.Dialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavGraph
import androidx.navigation.fragment.FragmentNavigator
import com.example.yakuba.MAIN
import com.example.yakuba.NavigationFragment
import com.example.yakuba.R
import com.example.yakuba.databinding.FragmentUserInformationBinding


class UserInformationFragment : Fragment() {

    private lateinit var binding:FragmentUserInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserInformationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        dialogUI()
        updateButton()
        mainNavigator()
    }

        private fun initUI() {
            with(binding) {
                val name = name.text.toString()
                val userName = userName.text.toString()
                val email = Email.text.toString()

                val TextWatcher = object:TextWatcher{
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                    override fun afterTextChanged(s: Editable?) {
                        updateButton()
                    }
                }

                binding.name.addTextChangedListener(TextWatcher)
                binding.userName.addTextChangedListener(TextWatcher)
                binding.Email.addTextChangedListener(TextWatcher)

            }

        }

        private fun allEditText(): Boolean {
            with(binding) {
                val name = name.text.toString()
                val userName = userName.text.toString()
                val email = Email.text.toString()

                return name.isNotEmpty() && userName.isNotEmpty() && email.isNotEmpty()
            }
        }

        private fun updateButton() {
            val greyWhite = ContextCompat.getColor(requireContext(), R.color.grey_white)
            with(binding) {
                if (allEditText()) {
                    completeAuth.setTextColor(Color.WHITE )
                    completeAuth.backgroundTintList = ColorStateList.valueOf(Color.RED)
                } else {
                    completeAuth.setTextColor(Color.GRAY)
                    completeAuth.backgroundTintList= ColorStateList.valueOf(greyWhite)
                }
            }
        }


        private fun mainNavigator() {
            with(binding) {
                completeAuth.setOnClickListener() {
                    if(allEditText()) {
                        NavigationFragment.NavigationMain(MAIN.navController)
                    }
                }

            }
        }



        private fun dialogUI() {
            binding.addPhoto.setOnClickListener() {
                val dialogBinding = layoutInflater.inflate(R.layout.dilog_choose_inf, null)
                val myDialog = Dialog(requireContext())
                myDialog.setContentView(dialogBinding)
                myDialog.setCancelable(true)
                myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                myDialog.show()

                val gallery = dialogBinding.findViewById<TextView>(R.id.gallery)

                gallery.setOnClickListener() {
                    val intent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(intent, GALLERY_REQUEST_CODE)
                }
            }
        }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK && data != null ) {
            val selectedImageUri= data.data
            if(selectedImageUri != null ) {
                if (selectedImageUri != null )
                    binding.aroundImg.setImageURI(selectedImageUri)
            }
        }
    }

    companion object {
        private const val GALLERY_REQUEST_CODE = 100
    }

}