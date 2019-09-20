package com.tiki.hometest.ui.views

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.tiki.hometest.R
import com.tiki.hometest.vo.Keyword
import kotlinx.android.synthetic.main.view_keywords.view.*





class KeywordContainerView : RelativeLayout {
    constructor(context: Context) : super(context) {
        setLayout(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setLayout(context, attrs)
    }

    private fun setLayout(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.view_keywords, this)
    }
    fun update(keywords: List<Keyword>?){
        keywordsContainer.removeAllViews()
        keywords?.forEach{
            val keywordView = KeywordView(context)
            val params = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            params.setMargins(16, 0,0,0)
            keywordView.layoutParams = params
            keywordView.update(it)
            keywordsContainer.addView(keywordView)
        }
    }

}