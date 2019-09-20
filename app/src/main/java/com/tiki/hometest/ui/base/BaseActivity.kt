package com.tiki.hometest.ui.base


import android.os.Bundle
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseActivity : AppCompatActivity() {
    protected val job = SupervisorJob()
    protected val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = getLayoutId()
        if (layoutId!= null){
            setupContentView(layoutId)
            setupUI()
        }
    }
    protected open fun setupContentView(layoutId: Int){
        setContentView(layoutId)
    }


    protected open fun setupUI(){}
    @LayoutRes
    protected abstract fun getLayoutId(): Int?

    @CallSuper
    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return super.onSupportNavigateUp()
    }
    protected fun showToast(@StringRes content: Int, vararg args: Any?){
        showToast(String.format(getString(content), args))
    }

    protected fun showToast(@StringRes content: Int) {
        showToast(getString(content))
    }

    protected fun showToast(content: String) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
    }
}
