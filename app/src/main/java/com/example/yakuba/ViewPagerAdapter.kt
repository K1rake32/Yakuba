package com.example.yakuba

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter: RecyclerView.Adapter<PagerMain>() {

    private val colors = intArrayOf(
        android.R.color.black,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerMain =
        PagerMain(LayoutInflater.from(parent.context).inflate(R.layout.pager_item, parent, false))

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: PagerMain, position: Int) = holder.itemView.run {

    }

}

class PagerMain(itemView:View) : RecyclerView.ViewHolder(itemView)