package com.tiki.hometest.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.android.example.github.di.Injectable
import com.tiki.hometest.R
import com.tiki.hometest.databinding.ActivityMainBinding
import com.tiki.hometest.testing.OpenForTesting
import com.tiki.hometest.ui.base.BaseBindActivity
import com.tiki.hometest.ui.common.RetryCallback
import javax.inject.Inject
@OpenForTesting
class MainActivity : BaseBindActivity<ActivityMainBinding>(), Injectable {
    override fun getLayoutId(): Int? {
        return R.layout.activity_main
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var vm: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.keywords = vm.keyword
        binding.callback = object: RetryCallback{
            override fun retry() {
                vm.retry()
            }

        }
        vm.keyword.observe(this, Observer {
            val a = 1
        })
        vm.loadKeywords()
    }
}
