package com.example.yakuba.UI.UI.Main.profile

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.transition.Visibility
import com.example.yakuba.MAIN
import com.example.yakuba.NavigationFragment
import com.example.yakuba.R
import com.example.yakuba.databinding.FragmentFeedbackBinding
import com.example.yakuba.databinding.FragmentMainStoryBinding


class FeedbackFragment : Fragment() {

    private lateinit var binding: FragmentFeedbackBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedbackBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        navigationUI()
    }

    private fun initUI() {

        with(binding) {

            val textWatcher = object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    updateButton()
                }
            }

            nameEdit.addTextChangedListener(textWatcher)
            mailEdit.addTextChangedListener(textWatcher)
            applicationEdit.addTextChangedListener(textWatcher)

        }
    }

    private fun allEditText(): Boolean {
        with(binding) {
            val name = nameEdit.text.toString()
            val mail = mailEdit.text.toString()
            val application = applicationEdit.text.toString()

            return name.isNotEmpty() && mail.isNotEmpty() && application.isNotEmpty()
        }
    }

    private fun updateButton() {
        val greyWhite = ContextCompat.getColor(requireContext(), R.color.grey_white)
        with(binding) {
            if (allEditText()) {
                sentApplication.setTextColor(Color.WHITE )
                sentApplication.backgroundTintList = ColorStateList.valueOf(Color.RED)
            } else {
                sentApplication.setTextColor(Color.GRAY)
                sentApplication.backgroundTintList= ColorStateList.valueOf(greyWhite)
            }
        }
    }

    private fun toastBroadcast() {
        val toast = Toast.makeText(requireContext(), "", Toast.LENGTH_LONG)
        val inflater: LayoutInflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.custom_toast_broadcast, null)

        toast.apply {
            setGravity(Gravity.BOTTOM, 0, 200)
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }

    private fun navigationUI() {
        with(binding) {
            backIcon.setOnClickListener() {
                NavigationFragment.NavigationFeedbackBack(MAIN.navController)
            }
                sentApplication.setOnClickListener() {
                    if (allEditText()) {
                        NavigationFragment.NavigationFeedbackBack(MAIN.navController)
                        toastBroadcast()
                    }
                }
        }
    }
}