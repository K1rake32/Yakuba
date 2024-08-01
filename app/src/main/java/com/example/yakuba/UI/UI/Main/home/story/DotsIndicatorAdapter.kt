package com.example.yakuba.UI.UI.Main.home.story

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.yakuba.R

class DotsAdapter(private val context: Context, private var itemCount: Int) : RecyclerView.Adapter<DotsAdapter.DotViewHolder>() {

    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.dots_item, parent, false)
        return DotViewHolder(view)
    }
    override fun onBindViewHolder(holder: DotViewHolder, position: Int) {
        if (position == selectedPosition) {
            holder.dot.setImageResource(R.drawable.for_dots_active)
        } else {
            holder.dot.setImageResource(R.drawable.for_dots_inactive)
        }
    }

    override fun getItemCount(): Int {
        return itemCount
    }

    fun setSelectedPosition(position: Int) {
        val previousPosition = selectedPosition
        selectedPosition = position
        notifyItemChanged(previousPosition)
        notifyItemChanged(selectedPosition)
    }

    inner class DotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dot: ImageView = itemView.findViewById(R.id.dot)
    }
}