package com.mobilecomputing.newsapp.screens.news

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilecomputing.newsapp.data.DataOrException
import com.mobilecomputing.newsapp.model.news.NewsData
import com.mobilecomputing.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    val data : MutableState<DataOrException<NewsData, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

//    init {
//        getNewsData(false, "delhi", "in", "general", "publishedAt")
//    }

    fun getNewsData(isTopHeadline: Boolean, q: String, country: String, category: String, sortBy: String) {
        // Call the repository to get the data
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getNewsData(isTopHeadline, q, country, category, sortBy)
            if (data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
            }

            Log.d("INSIDE LAUNCH", "getNewsData: ${data.value.data}")
        }
    }
}