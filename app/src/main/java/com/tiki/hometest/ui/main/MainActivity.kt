package com.tiki.hometest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.android.example.github.di.Injectable
import com.tiki.hometest.R
import com.tiki.hometest.databinding.ActivityMainBinding
import com.tiki.hometest.ui.base.BaseBindActivity
import com.tiki.hometest.ui.common.RetryCallback
import com.tiki.hometest.vo.Status
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseBindActivity<ActivityMainBinding>(), Injectable {
    override fun getLayoutId(): Int? {
        return R.layout.activity_main
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)
        mainViewModel.loadKeywords()
        binding.lifecycleOwner = this
        binding.keywords = mainViewModel.keyword
        binding.callback = object: RetryCallback{
            override fun retry() {
                mainViewModel.retry()
            }

        }
    }
}
