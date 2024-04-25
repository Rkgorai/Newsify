package com.mobilecomputing.newsapp.screens.news

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilecomputing.newsapp.data.DataOrException
import com.mobilecomputing.newsapp.model.news.Article
import com.mobilecomputing.newsapp.model.news.NewsData
import com.mobilecomputing.newsapp.repository.NewsRepository
import com.mobilecomputing.newsapp.utils.Constant.state_location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    val data : MutableState<DataOrException<NewsData, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    var topHeadlineArticles = mutableStateOf(listOf<Article>())
    var everythingArticles  = mutableStateOf(listOf<Article>())

    init {

        getNewsData(true, state_location.value, "in", "general", "publishedAt")
        getNewsData(false, state_location.value, "in", "general", "publishedAt")

    }

    fun getNewsData(isTopHeadline: Boolean, q: String, country: String, category: String, sortBy: String) {
        // Call the repository to get the data
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getNewsData(isTopHeadline, q, country, category, sortBy)
            if (data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
            }

            // Assign the articles to the correct list after the data has been fetched
            if (isTopHeadline) {
                topHeadlineArticles.value = data.value.data?.articles ?: listOf()
            } else {
                everythingArticles.value = data.value.data?.articles ?: listOf()
            }
        }
    }
}