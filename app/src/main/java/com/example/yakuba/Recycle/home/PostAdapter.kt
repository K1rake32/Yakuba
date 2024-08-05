package com.example.yakuba.Recycle.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yakuba.MAIN
import com.example.yakuba.NavigationFragment
import com.example.yakuba.R
import com.example.yakuba.Recycle.coment.Person
import com.example.yakuba.Recycle.coment.PersonAdapter
import com.example.yakuba.databinding.PersonItemBinding
import com.example.yakuba.databinding.PostItemBinding
import kotlinx.coroutines.flow.flowOf

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    var data: MutableList<Post> = mutableListOf()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    inner class PostViewHolder(val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(inflater, parent, false)

        return PostViewHolder(binding)

    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = data[position]

        with(holder.binding) {
            textDataHome.text = post.data
            title.text = post.title

            pager.adapter = PostPagerAdapter(post.ImageId)

            description.text = post.description
            textLike.text = post.likes
            textComent.text = post.comment

            dotsIndicator.setViewPager(pager)

            nextText.setOnClickListener() {
                NavigationFragment.NavgationDetails(MAIN.navController)
            }

            var isHeartFilled = false
            val heart = R.drawable.heart
            val heartRed = R.drawable.likes_active

            textLike.setCompoundDrawablesWithIntrinsicBounds(heart, 0, 0, 0)

            val quanityLikes = textLike.text.toString()

            var quanityInt = quanityLikes.toIntOrNull() ?: 0


            textLike.setOnClickListener {
                if (!isHeartFilled) {
                    textLike.setCompoundDrawablesWithIntrinsicBounds(heartRed, 0, 0, 0)
                    quanityInt += 1
                } else {
                    textLike.setCompoundDrawablesWithIntrinsicBounds(heart, 0, 0, 0)
                    quanityInt -= 1
                }
                isHeartFilled = !isHeartFilled
                textLike.text = quanityInt.toString()
            }

        }
    }

    fun addPost(post: Post) {
        data.add(0, post)
        notifyItemInserted(0)
    }

}
