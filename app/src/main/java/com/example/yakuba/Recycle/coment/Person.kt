package com.example.yakuba.Recycle.coment

import android.net.Uri

data class Person(
    val name: String,
    val description: String,
    val data: String,
    val avatarUri: Uri? = null,
    val image: Int? = null
)