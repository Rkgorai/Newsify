package com.mobilecomputing.newsapp.screens.news

import android.util.Log
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

    var localNewsArticles  = mutableStateOf(listOf<Article>())
    var topHeadlineArticlesGeneral = mutableStateOf(listOf<Article>())
    var topHeadlineArticlesEntertainment = mutableStateOf(listOf<Article>())
    var topHeadlineArticlesHealth = mutableStateOf(listOf<Article>())
    var topHeadlineArticlesScience = mutableStateOf(listOf<Article>())
    var topHeadlineArticlesSports = mutableStateOf(listOf<Article>())
    var topHeadlineArticlesTechnology = mutableStateOf(listOf<Article>())

    init {}

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
                when(category) {
                    "general" -> topHeadlineArticlesGeneral.value = data.value.data?.articles ?: listOf()
                    "entertainment" -> topHeadlineArticlesEntertainment.value = data.value.data?.articles ?: listOf()
                    "health" -> topHeadlineArticlesHealth.value = data.value.data?.articles ?: listOf()
                    "science" -> topHeadlineArticlesScience.value = data.value.data?.articles ?: listOf()
                    "sports" -> topHeadlineArticlesSports.value = data.value.data?.articles ?: listOf()
                    "technology" -> topHeadlineArticlesTechnology.value = data.value.data?.articles ?: listOf()
                    else -> {} // Handle the case where category does not match any of the above
                }
//                topHeadlineArticlesGeneral.value = data.value.data?.articles ?: listOf()
            } else {
                localNewsArticles.value = data.value.data?.articles ?: listOf()
            }
        }
    }

    fun getArticleById(id: String): Article? {
        val allArticles = listOf(
            *localNewsArticles.value.toTypedArray(),
            *topHeadlineArticlesGeneral.value.toTypedArray(),
            *topHeadlineArticlesEntertainment.value.toTypedArray(),
            *topHeadlineArticlesHealth.value.toTypedArray(),
            *topHeadlineArticlesScience.value.toTypedArray(),
            *topHeadlineArticlesSports.value.toTypedArray(),
            *topHeadlineArticlesTechnology.value.toTypedArray()
        )

        val article = allArticles.find { it.title == id }
        if (article == null) {
            Log.d("NewsViewModel", "No article found with id: $id")
        } else {
            Log.d("NewsViewModel", "Found article with id: $id")
        }

        return article
    }


}