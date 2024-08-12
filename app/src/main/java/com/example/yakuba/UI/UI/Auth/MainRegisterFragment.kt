package com.example.yakuba.UI.UI.Auth

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.activityViewModels
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.yakuba.DataModel
import com.example.yakuba.MAIN
import com.example.yakuba.NavigationFragment
import com.example.yakuba.R
import com.example.yakuba.databinding.FragmentMainRegisterBinding
import com.redmadrobot.inputmask.MaskedTextChangedListener

class MainRegisterFragment : Fragment() {

    private lateinit var binding: FragmentMainRegisterBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(binding.constraintLayout) { view, insets ->
            val params = view.layoutParams as ViewGroup.MarginLayoutParams
            params.bottomMargin = insets.systemWindowInsetBottom
            view.layoutParams = params
            insets.consumeSystemWindowInsets()
        }


        view.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            if (keypadHeight > screenHeight * 0.15) {
                binding.constraintLayout.translationY = -keypadHeight.toFloat()
            } else {
                binding.constraintLayout.translationY = 0f
            }
        }

        init()
        initMediaPlayer()
        setupInputMask()
    }

    private fun init() {
        with(binding) {
            numberEdit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    updateContinueButtonState()
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            checkBox.setOnCheckedChangeListener { _, _ ->
                updateContinueButtonState()
            }

        }
    }

    private fun updateContinueButtonState() {
        with(binding) {
            val number = numberEdit.text.toString()
            val isEnabled = number.length == 16 && checkBox.isChecked
            continueButton.isEnabled = isEnabled
            if (isEnabled) {
                continueButton.backgroundTintList = ColorStateList.valueOf(Color.RED)
                continueButton.setTextColor(Color.WHITE)
                continueButton.setOnClickListener() {
                    initNumber()
                    NavigationFragment.NavigationAuth(MAIN.navController)
                }
            } else {
                val greyWhite = ContextCompat.getColor(requireContext(), R.color.grey_white)
                continueButton.backgroundTintList = ColorStateList.valueOf(greyWhite)
                continueButton.setTextColor(Color.GRAY)
            }
        }
    }

    private fun initMediaPlayer() {
        val player = ExoPlayer.Builder(requireContext())
            .build()

        binding.PlayerFAQ.player = player

        val vidoUri =
            Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.yakuba)

        val video = MediaItem.Builder()
            .setUri(vidoUri)
            .build()

        player.addMediaItem(video)
        player.prepare()
        player.repeatMode = Player.REPEAT_MODE_ALL
        player.playWhenReady = true
    }


    private fun initNumber() {
        val number = binding.numberEdit.text.toString()
        val numberPlus = "+7$number"

        val fullNumber =
            "Для подтверждения\nна номер $numberPlus поступит\nзвонок, введите последние 4 цифры."

        val spanableString = SpannableString(fullNumber)

        val start = fullNumber.indexOf(numberPlus)
        val end = start + numberPlus.length

        spanableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.red)),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        dataModel.message.value = spanableString

    }


    private fun setupInputMask() {

        val listener = MaskedTextChangedListener(
            " ([000]) [000]-[00]-[00]",
            binding.numberEdit
        )

        binding.numberEdit.addTextChangedListener(listener)
        binding.numberEdit.onFocusChangeListener = listener
    }
}