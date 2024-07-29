package com.example.yakuba.Recycle.addPost

import android.content.Context
import com.example.yakuba.R
import com.example.yakuba.Recycle.coment.Person

class AddPostRecycle(context: Context) {

    private var addPost = mutableListOf<AddPost>()

    init {
        addPost = mutableListOf(
            AddPost(R.drawable.add_button)
        )
    }

    public fun getAddPost(): MutableList<AddPost> {
        return addPost
    }

}