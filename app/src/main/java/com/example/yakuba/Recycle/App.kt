package com.example.yakuba.Recycle

import android.app.Application
import com.example.yakuba.Recycle.addPost.AddPostRecycle
import com.example.yakuba.Recycle.coment.PersonRecycle
import com.example.yakuba.Recycle.gallery.GalleryRecycle
import com.example.yakuba.Recycle.home.PostRecycle

class App: Application() {

    lateinit var personService: PersonRecycle
    lateinit var postService: PostRecycle
    lateinit var galleryService: GalleryRecycle
    lateinit var addPostService: AddPostRecycle

    override fun onCreate() {
        super.onCreate()
        personService = PersonRecycle(this)
        postService = PostRecycle(this)
        galleryService = GalleryRecycle(this)
        addPostService = AddPostRecycle(this)
    }

}