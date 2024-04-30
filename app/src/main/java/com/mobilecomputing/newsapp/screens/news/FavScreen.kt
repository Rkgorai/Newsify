package com.mobilecomputing.newsapp.screens.news

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.mobilecomputing.newsapp.component.ArticleItemSecond
import com.mobilecomputing.newsapp.database.ArticleDao
import com.mobilecomputing.newsapp.database.NewsArticle
import kotlinx.coroutines.launch

@Composable
fun FavScreen(dao: ArticleDao) {
    val scope = rememberCoroutineScope()
    val articles = remember { mutableStateOf(listOf<NewsArticle>()) }

    LaunchedEffect(Unit) {
        scope.launch {
            articles.value = dao.getAllArticles()
        }
    }

    if (articles.value.isEmpty()) {
        Text("No articles found")
    } else {
        LazyColumn {
            items(articles.value) { article ->
                ArticleItemSecond(listOf(article))
            }
        }
    }
}