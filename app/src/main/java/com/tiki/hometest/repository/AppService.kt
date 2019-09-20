package com.tiki.hometest.repository

import androidx.lifecycle.LiveData
import com.tiki.hometest.vo.Resource

interface AppService {
    fun getKeywords() : LiveData<Resource<List<String>>>
}