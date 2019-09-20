package com.tiki.hometest.ui.views

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.tiki.hometest.R
import com.tiki.hometest.ui.ShapeUtils
import kotlinx.android.synthetic.main.view_keyword.view.*

class KeywordView : RelativeLayout {
    constructor(context: Context) : super(context) {
        setLayout(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setLayout(context, attrs)
    }

    private fun setLayout(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.view_keyword, this)
        setColor(ContextCompat.getColor(context, R.color.blue_500))
    }
    fun setColor(color: Int){
        val drawable = ShapeUtils.createDrawable(color, color, 16f)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            containerKeyWord.background = drawable
        }else{
            containerKeyWord.setBackgroundDrawable(drawable)
        }
    }
}