package com.example.yakuba.Recycle

import android.app.Application
import com.example.yakuba.Recycle.coment.PersonRecycle
import com.example.yakuba.Recycle.home.PostRecycle

class App: Application() {

    lateinit var personService: PersonRecycle
    lateinit var postService: PostRecycle

    override fun onCreate() {
        super.onCreate()
        personService = PersonRecycle(this)
        postService = PostRecycle(this)
    }



}