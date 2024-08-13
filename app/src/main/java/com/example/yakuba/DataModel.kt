package com.example.yakuba

import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yakuba.Recycle.home.Post

class DataModel : ViewModel() {

    val message: MutableLiveData<SpannableString?> by lazy {
        MutableLiveData<SpannableString?>()
    }

    val number: MutableLiveData<SpannableString?> by lazy {
        MutableLiveData<SpannableString?>()
    }

    val name: MutableLiveData<SpannableString?> by lazy {
        MutableLiveData<SpannableString?>()
    }

    val sername: MutableLiveData<SpannableString?> by lazy {
        MutableLiveData<SpannableString?>()
    }

    val email: MutableLiveData<SpannableString?> by lazy {
        MutableLiveData<SpannableString?>()
    }

    val userAvatar: MutableLiveData<Uri?> by lazy {
        MutableLiveData<Uri?>()
    }

    private val _posts = MutableLiveData<MutableList<Post>>().apply { value = mutableListOf() }
    val posts: LiveData<MutableList<Post>> get() = _posts

    fun addPost(post: Post) {
        _posts.value?.let {
            it.add(post)
            _posts.value = it
        }
    }
}