package com.tiki.hometest.api

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubUserContentService {
    @GET("/tikivn/android-home-test/v2/keywords.json")
    fun getKeywords(): LiveData<ApiResponse<List<String>>>
}