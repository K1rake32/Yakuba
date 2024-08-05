package com.example.yakuba.Recycle.addPost

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.example.yakuba.Recycle.coment.Person
import com.example.yakuba.Recycle.home.Post
import com.example.yakuba.databinding.ItemAddPostBinding
import com.example.yakuba.databinding.PersonItemBinding

class AddPostAdapter(
    private val activityResultLauncher: ActivityResultLauncher<Intent>
) : RecyclerView.Adapter<AddPostAdapter.AddPostViewHolder>() {

    var data: MutableList<AddPost> = mutableListOf()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    inner class AddPostViewHolder(val binding: ItemAddPostBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAddPostBinding.inflate(inflater, parent, false)
        return AddPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddPostViewHolder, position: Int) {
        val addPost = data[position]

        with(holder.binding) {
            when (addPost.img) {
                is Int -> img.setImageResource(addPost.img as Int) // Set image from resource
                is String -> img.setImageURI(Uri.parse(addPost.img as String)) // Set image from URI
            }

            img.setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                    type = "image/*"
                    putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                    addCategory(Intent.CATEGORY_OPENABLE)
                }
                activityResultLauncher.launch(intent)
            }

            val countItem = itemCount
            if (position > 0) {
                removeItem.visibility = View.VISIBLE
                removeItem.setOnClickListener {
                    removePostAtPosition(position)
                }
            } else {
                removeItem.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int = data.size

    fun addPosts(addPosts: List<AddPost>) {
        data.addAll(addPosts)
        notifyDataSetChanged()
    }

    fun getImagesWithoutFirst(): List<Any> {
        return if (data.isNotEmpty()) {
            data.drop(1).map { it.img }
        } else {
            emptyList()
        }
    }

        private fun removePostAtPosition(position: Int) {
        if (position in 0 until data.size) {
            data.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, data.size)
        }
    }

    fun keepOnlyOneItem() {
        if (data.size > 1) {
            val itemToKeep = data.first()
            data.clear()
            data.add(itemToKeep)
            notifyDataSetChanged()
        }
    }

    fun getSelectedImages(): List<Any> {
        return data.map { it.img as? Any ?: "" }
    }

}