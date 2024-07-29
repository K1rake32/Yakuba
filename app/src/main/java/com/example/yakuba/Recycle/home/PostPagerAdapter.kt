package com.example.yakuba.Recycle.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yakuba.databinding.PostPagerItemBinding

class PostPagerAdapter(private val imageIds: List<Int>) : RecyclerView.Adapter<PostPagerAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding: PostPagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageId: Int) {
            binding.img.setImageResource(imageId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostPagerItemBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageId = imageIds[position]
        holder.bind(imageId)
    }

    override fun getItemCount(): Int = imageIds.size
}