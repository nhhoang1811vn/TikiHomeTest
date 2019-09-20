package com.tiki.hometest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.tiki.hometest.api.ApiEmptyResponse
import com.tiki.hometest.api.ApiErrorResponse
import com.tiki.hometest.api.ApiSuccessResponse
import com.tiki.hometest.api.GithubUserContentService
import com.tiki.hometest.vo.Resource
import javax.inject.Inject

class AppServiceIpm @Inject constructor(private val githubUserContentService: GithubUserContentService) : AppService {
    override fun getKeywords(): LiveData<Resource<List<String>>> {
        val result = MediatorLiveData<Resource<List<String>>>()
        result.value = Resource.loading(null)
        result.addSource(githubUserContentService.getKeywords()){
            when(it){
                is ApiSuccessResponse -> {
                    result.value = Resource.success(it.body)
                }
                is ApiEmptyResponse -> {
                    result.value = Resource.error("empty response",null)
                }
                is ApiErrorResponse -> {
                    result.value = Resource.error(it.errorMessage,null)
                }
            }
        }
        return result
    }
}