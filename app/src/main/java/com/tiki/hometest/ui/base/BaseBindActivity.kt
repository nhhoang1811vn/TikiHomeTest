package com.tiki.hometest.ui.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindActivity<T: ViewDataBinding> : BaseActivity() {
    protected lateinit var binding: T
    override fun setupContentView(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)
    }
}