package com.example.yakuba.Recycle.gallery

import android.content.Context
import com.example.yakuba.R
import com.example.yakuba.Recycle.coment.Person

class GalleryRecycle(context: Context) {

    private var gallery = mutableListOf<Gallery>()

    init {

        val description1 = context.getString(R.string.gallery_description1)
        val description2 = context.getString(R.string.gallery_description2)
        val description3 = context.getString(R.string.gallery_description3)

        gallery = mutableListOf(
            Gallery(R.drawable.png_test1, "15 марта 2022", "Омск", description = description1),
            Gallery(R.drawable.image2_gallery, "10 марта 2022", "Тюмень", description = description2),
            Gallery(R.drawable.image3_gallery, "27 февраля 2022","Сочи", description = description3),
            Gallery(R.drawable.image1_gallery, "15 февраля 2022", "Калуга", description = description1),
            Gallery(R.drawable.image2_gallery, "12 января 2022", "Новосибирск", description = description2),
            Gallery(R.drawable.image3_gallery, "1 января", "Москва", description = description3),
            Gallery(R.drawable.image3_gallery, "1 января", "Москва", description = description3),
            Gallery(R.drawable.image3_gallery, "1 января", "Москва", description = description3),
            Gallery(R.drawable.image3_gallery, "1 января", "Москва", description = description3),
            Gallery(R.drawable.image3_gallery, "1 января", "Москва", description = description3),
        )

    }

    public fun getGallery(): MutableList<Gallery> {
        return gallery
    }

}