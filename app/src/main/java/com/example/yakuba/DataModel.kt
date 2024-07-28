package com.example.yakuba

import android.text.Spannable
import android.text.SpannableString
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel: ViewModel() {
    val message: MutableLiveData<SpannableString?> by lazy {
        MutableLiveData<SpannableString?>()
    }

    val name: MutableLiveData<SpannableString?> by lazy {
        MutableLiveData<SpannableString?>()
    }

}