package com.example.yakuba.Recycle.gallery

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration(private val itemOffset: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = itemOffset
        outRect.right = itemOffset
        outRect.bottom = itemOffset

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = itemOffset
        } else {
            outRect.top = 0
        }
    }
}