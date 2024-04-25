package com.mobilecomputing.newsapp.repository

import android.util.Log
import com.mobilecomputing.newsapp.data.DataOrException
import com.mobilecomputing.newsapp.model.news.NewsData
import com.mobilecomputing.newsapp.network.NewsAPI
import javax.inject.Inject

class NewsRepository @Inject constructor(private val api: NewsAPI) {
    private val dataOrException = DataOrException<NewsData, Boolean, Exception>()

    suspend fun getNewsData(isTopHeadline: Boolean ,q: String, country: String, category: String, sortBy: String): DataOrException<NewsData, Boolean, Exception> {
        try {
            dataOrException.loading = true
            if (isTopHeadline) {
                dataOrException.data = api.getTopHeadlines(category, country, sortBy)
            } else {
                dataOrException.data = api.getEverything(q, sortBy)
            }

            if (dataOrException.data.toString().isNotEmpty()) {
                dataOrException.loading = false
            }
        } catch (e: Exception) {
            dataOrException.e = e
            Log.d("EXCEPTION", "getNewsData: ${e.message}")

        }
        return dataOrException
    }
}