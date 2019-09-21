package com.tiki.hometest.ui.main

import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.tiki.hometest.R
import com.tiki.hometest.repository.AppService
import com.tiki.hometest.testing.OpenForTesting
import com.tiki.hometest.ui.Utils
import com.tiki.hometest.vo.Keyword
import com.tiki.hometest.vo.Resource
import java.util.*
import javax.inject.Inject
import kotlin.math.abs
@OpenForTesting
class MainViewModel @Inject constructor(appService: AppService) : ViewModel() {

    private val _keywordInput = MutableLiveData<Boolean>()
    private val _keywords = Transformations.switchMap(_keywordInput){
        appService.getKeywords()
    }

    val keyword : LiveData<Resource<List<Keyword>>> = Transformations.map(_keywords){
        var data : MutableList<Keyword>?= null
        if (it.data != null){
            data = mutableListOf()
            it.data.forEach {
                val text = Utils.getKeywordString(it)
                val random = Random()
                val index = random.nextInt(keywordColors.size)
                val color = keywordColors[index]
                val keyword = Keyword(text,color)
                data.add(keyword)
            }
        }

        Resource(it.status, data, it.message)
    }



    fun loadKeywords(){
        _keywordInput.value = true
    }
    fun retry(){
        _keywordInput.value?.let {
            _keywordInput.value = it
        }
    }
    companion object{
        val keywordColors = mutableListOf(R.color.green_700
        ,R.color.red_700
            ,R.color.blue_700
            ,R.color.purple_700
            ,R.color.pink_700
            ,R.color.indigo_700
            ,R.color.cyan_700
            ,R.color.amber_700
            ,R.color.brown_700
            ,R.color.teal_700

        )
    }
}

