package com.example.yakuba.Recycle.home

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yakuba.databinding.PostPagerItemBinding

class PostPagerAdapter(private val imageIds: List<Any>) : RecyclerView.Adapter<PostPagerAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding: PostPagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Any) {
            when (image) {
                is Int -> binding.img.setImageResource(image)
                is String -> binding.img.setImageURI(Uri.parse(image))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostPagerItemBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = imageIds[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int = imageIds.size
}