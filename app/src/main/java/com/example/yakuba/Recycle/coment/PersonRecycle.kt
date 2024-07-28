package com.example.yakuba.Recycle.coment

import android.content.Context
import com.example.yakuba.R
import com.example.yakuba.Recycle.coment.Person

class PersonRecycle(context:Context) {

    private var persons = mutableListOf<Person>()

    init {
        val userName1 = context.getString(R.string.userName1)
        val data1 = context.getString(R.string.TimeUser1)
        val description1 = context.getString(R.string.descriptionUser1)

        val userName2 = context.getString(R.string.userName2)
        val data2 = context.getString(R.string.TimeUser2)
        val description2 = context.getString(R.string.descriptionUser2)

        val userName3 = context.getString(R.string.userName3)
        val data3 = context.getString(R.string.TimeUser3)
        val description3 = context.getString(R.string.descriptionUser3)

        persons = mutableListOf(
            Person(name = userName1, data = data1, description = description1, image = R.drawable.user_detail),
            Person(name = userName2, data = data2, description = description2, image = R.drawable.user),
            Person(name = userName3, data = data3, description = description3, image = R.drawable.user)
        )
    }

    public fun getPersons(): MutableList<Person> {
        return persons
    }

}