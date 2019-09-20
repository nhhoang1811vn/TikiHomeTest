package com.tiki.hometest.binding


import android.view.View
import androidx.databinding.BindingAdapter

import com.tiki.hometest.ui.views.KeywordContainerView
import com.tiki.hometest.vo.Keyword
import com.tiki.hometest.vo.Resource


object BindingAdapters {
    @JvmStatic
    @BindingAdapter("data")
    fun setData(view: KeywordContainerView, resource: Resource<List<Keyword>>?) {
        view.update(resource?.data)
    }

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}