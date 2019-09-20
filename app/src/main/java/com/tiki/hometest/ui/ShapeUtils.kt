package com.tiki.hometest.ui

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable


object ShapeUtils {

    fun createDrawable(backgroundColor: Int?, borderColor: Int?, cornerRadius: Float): Drawable {
        return createGradientDrawable(backgroundColor, borderColor, cornerRadius)
    }

    fun createGradientDrawable(backgroundColor: Int?, borderColor: Int?, cornerRadius: Float): Drawable {
        var shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadius = cornerRadius
        if (backgroundColor != null) {
            shape.setColor(backgroundColor)
        }
        if (borderColor != null) {
            shape.setStroke(3, borderColor)
        }
        return shape
    }
}