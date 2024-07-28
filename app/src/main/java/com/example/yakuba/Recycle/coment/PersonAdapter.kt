package com.example.yakuba.Recycle.coment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yakuba.databinding.PersonItemBinding

class PersonAdapter: RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    var data: MutableList<Person> = mutableListOf()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    inner class PersonViewHolder(val binding: PersonItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PersonItemBinding.inflate(inflater, parent, false)

        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = data[position]

        with(holder.binding) {
            NameText.text = person.name
            dataText.text = person.data
            descriptionText.text = person.description
            userImage.setImageResource(person.image)
        }
    }

    override fun getItemCount(): Int = data.size

    fun addPerson(person: Person) {
        data.add(0, person)
        notifyItemInserted(0)
    }

}