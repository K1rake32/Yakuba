package com.example.yakuba.UI.UI.Auth

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.yakuba.DataModel
import com.example.yakuba.MAIN
import com.example.yakuba.NavigationFragment
import com.example.yakuba.R
import com.example.yakuba.databinding.FragmentNubmerConfirmationBinding

class NubmerConfirmationFragment : Fragment() {

    private val dataModel: DataModel by activityViewModels()
    private lateinit var binding:FragmentNubmerConfirmationBinding
    private var timer:CountDownTimer? = null
    private var isTimer: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNubmerConfirmationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        dataModelNumber()
        timerUI()
        navigationUI()
    }

    private fun initUI() {
        with(binding) {
            pinView.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val pin = s.toString()
                    if(pin == "1111") {
                        NavigationFragment.NavigationInf(MAIN.navController)
                    } else if (pin.length >= 4 && pin !== "1111"){
                        pinView.setTextColor(Color.RED)
                        pinView.setLineColor(Color.RED)
                        ToastError()
                    } else {
                        pinView.setTextColor(Color.BLACK)
                        pinView.setLineColor(Color.BLACK)
                    }
                }
            })
        }
    }



    private fun dataModelNumber() {
        dataModel.message.observe(activity as LifecycleOwner, {it
            binding.textConfirmation.text = it
        })
    }

    fun ToastError() {
        val toast = Toast.makeText(requireContext(), "", Toast.LENGTH_LONG)
        val inflater: LayoutInflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.toast_error, null)

        toast.apply {
            setGravity(Gravity.BOTTOM, 0, 550)
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }

    private fun timerUI() {

        binding.apply {
            timerText.setOnClickListener() {
                startCountDawnTimer(10000)
            }
        }

    }

    private fun startCountDawnTimer(timeMillis: Long) {

        if (timer != null) {
            return
        }

        with(binding) {
            timerText.setTextColor(Color.GRAY)

            timer = object : CountDownTimer(timeMillis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val secondsRemaining = millisUntilFinished / 1000

                    if (secondsRemaining in 2..4) {
                        timerText.text = "Позвонить еще раз через $secondsRemaining секунды"
                    } else if (secondsRemaining == 1L) {
                        timerText.text = "Позвонить еще раз через $secondsRemaining секунду"
                    } else {
                        timerText.text = "Позвонить еще раз через $secondsRemaining секунд"
                    }

                    if (secondsRemaining == 0L) {
                        return onFinish()
                    }

                }

                override fun onFinish() {
                    timerText.text = "Заказать звонок"
                    timerText.setTextColor(Color.RED)
                    timer = null
                }

            }.start()
        }
    }

    private fun navigationUI() {
        binding.backIcon.setOnClickListener() {
            NavigationFragment.NavigationAuthBack(MAIN.navController)
        }
    }

}