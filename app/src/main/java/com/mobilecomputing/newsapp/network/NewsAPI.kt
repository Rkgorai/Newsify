package com.mobilecomputing.newsapp.network

import com.mobilecomputing.newsapp.model.news.NewsData
import com.mobilecomputing.newsapp.utils.Constant.NEWS_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface NewsAPI {

    @GET("top-headlines?apiKey=${NEWS_API_KEY}")
    suspend fun getTopHeadlines(
//        @Query("q") query: String = "Delhi",
        @Query("category") category: String = "general",
        @Query("country") country: String = "in",
        @Query("sortBy") sortBy: String = "publishedAt"
    ): NewsData

    @GET("everything?apiKey=${NEWS_API_KEY}")
    suspend fun getEverything(
        @Query("q") query: String= "Delhi",
        @Query("sortBy") sortBy: String = "publishedAt"
    ): NewsData
}