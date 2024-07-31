package com.example.yakuba.Recycle.coment

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yakuba.R
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

    private fun showDialog(context: Context, position: Int) {
        val dialogBinding = LayoutInflater.from(context).inflate(R.layout.item_delete_coment, null)
        val myDialog = Dialog(context)
        myDialog.setContentView(dialogBinding)
        myDialog.setCancelable(true)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        myDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        myDialog.window?.setGravity(Gravity.BOTTOM)

        myDialog.show()

        val deleteComent = dialogBinding.findViewById<TextView>(R.id.deleteComent)

        deleteComent.setOnClickListener {
            removePostAtPosition(position)
            myDialog.dismiss()
        }
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = data[position]

        with(holder.binding) {
            NameText.text = person.name
            dataText.text = person.data
            descriptionText.text = person.description
            userImage.setImageResource(person.image)

            moreComent.setOnClickListener() {
                showDialog(holder.itemView.context, position)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    fun addPerson(person: Person) {
        data.add(0, person)
        notifyItemInserted(0)
    }


    private fun removePostAtPosition(position: Int) {
        if (position in 0 until data.size) {
            data.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, data.size)
        }
    }
}