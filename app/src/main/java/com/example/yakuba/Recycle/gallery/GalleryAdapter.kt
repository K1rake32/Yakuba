package com.example.yakuba.Recycle.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yakuba.Recycle.coment.Person
import com.example.yakuba.databinding.GalleryItemBinding
import com.example.yakuba.databinding.PersonItemBinding

class GalleryAdapter: RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    var data: MutableList<Gallery> = mutableListOf()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    inner class GalleryViewHolder(val binding: GalleryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GalleryItemBinding.inflate(inflater, parent, false)

        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val gallery = data[position]

        with(holder.binding) {

            imageGallery.setImageResource(gallery.img)
            dataGallery.text = gallery.data
            city.text = gallery.city
            descriptionGallery.text = gallery.description

        }
    }

    override fun getItemCount(): Int = data.size

    fun addGallery(gallery: Gallery) {
        data.add(0, gallery)
        notifyItemInserted(0)
    }

}