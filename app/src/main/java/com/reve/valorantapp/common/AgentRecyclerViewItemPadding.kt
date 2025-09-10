package com.reve.valorantapp.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class AgentRecyclerViewItemPadding(private val edgePaddingPX: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        if (position % 2 == 0) {
            outRect.right = edgePaddingPX / 2
        } else {
            outRect.left = edgePaddingPX / 2
        }
    }
}