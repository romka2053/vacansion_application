package com.roman.core_ui

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView

class CustomItemDecorationHorizontal(
    private val context: Context,
    private val start: Int,
    private val between: Int,
    private val end: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val startPixels = dpToPixels(start).toInt()
        val betweenPixels=dpToPixels(between).toInt()
        val endPixels=dpToPixels(end).toInt()

        parent.adapter?.let { adapter ->
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = startPixels
                outRect.right = betweenPixels
            } else if (parent.getChildAdapterPosition(view) == adapter.itemCount -1) {
                outRect.right = endPixels

            } else {
                outRect.right = betweenPixels
            }

        }
    }

    private fun dpToPixels(dp: Int) =
        dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

}