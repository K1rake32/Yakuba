package com.example.yakuba.Recycle.home

import android.content.Context
import android.media.Image
import com.example.yakuba.R
import com.example.yakuba.Recycle.coment.Person

class PostRecycle(context: Context) {

    private var post = mutableListOf<Post>()

    init {

        val data1 = context.getString(R.string.data_time)
        val title1 = context.getString(R.string.kak)
        val description1 = context.getString(R.string.under_title)

        val image1 = listOf(
            R.drawable.details_image,
            R.drawable.yakuba_anons,
            R.drawable.rewiwse,
            R.drawable.details_image,
            R.drawable.details_image,
            R.drawable.details_image,
            )

        val image2 = listOf(
            R.drawable.rewiwse,
            R.drawable.reviews,
            R.drawable.yakuba_anons,
            R.drawable.details_image,
        )

        val data2 = context.getString(R.string.data_time2)
        val title2 = context.getString(R.string.kak2)
        val description2 = context.getString(R.string.under_title2)



        post = mutableListOf(
            Post(data = data1, title = title1, description = description1, "108", "72", image1),
            Post(data = data2, title = title2, description = description2, "115", "14", image2),
            Post(data = data1, title = title1, description = description1, "108", "72", image1 ),
            Post(data = data2, title = title2, description = description2, "115", "14", image2 ),
            Post(data = data1, title = title1, description = description1, "108", "72", image1 ),
            Post(data = data2, title = title2, description = description2, "115", "14", image2 ),
        )
    }

    public fun getPersons(): MutableList<Post> {
        return post
    }

}