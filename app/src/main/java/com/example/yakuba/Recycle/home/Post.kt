package com.example.yakuba.Recycle.home

import android.provider.ContactsContract.Data

data class Post (
    val data: String,
    val title: String,
    val description: String,
    val likes: String,
    val comment: String,
    val ImageId: List<Any>
)

