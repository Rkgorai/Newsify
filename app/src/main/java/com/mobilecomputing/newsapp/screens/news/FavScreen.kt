package com.mobilecomputing.newsapp.screens.news

import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mobilecomputing.newsapp.SecondMainActivity
import com.mobilecomputing.newsapp.component.ArticleItemSecond
import com.mobilecomputing.newsapp.database.ArticleDao
import com.mobilecomputing.newsapp.database.NewsArticle
import kotlinx.coroutines.launch


@Composable
fun FavScreen(dao: ArticleDao) {
    val scope = rememberCoroutineScope()
    val articles = remember { mutableStateOf(listOf<NewsArticle>()) }
    val isRefreshing = remember { mutableStateOf(false) }

    val context = LocalContext.current // Get the current context

    val startActivityLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        // Handle the result of the activity here
    }

    LaunchedEffect(Unit) {
        scope.launch {
            articles.value = dao.getAllArticles()
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing.value),
        onRefresh = {
            isRefreshing.value = true
            scope.launch {
                articles.value = dao.getAllArticles()
                isRefreshing.value = false
            }
        }
    ) {
        if (articles.value.isEmpty()) {
            Text("No articles found")
        } else {
            LazyColumn {
                items(articles.value.reversed()) { article ->
                    Box(modifier = Modifier.clickable {
                        // Create an Intent to start the new activity
                        val intent = Intent(context, SecondMainActivity::class.java)

                        intent.putExtra("title", article.title)
                        intent.putExtra("author", article.author)
                        intent.putExtra("publishedAt", article.publishedAt)
                        intent.putExtra("source", article.source)
                        intent.putExtra("url", article.url)
                        intent.putExtra("urlToImage", article.urlToImage)
                        intent.putExtra("content", article.content)
                        intent.putExtra("description", article.description)
                        // Use the launcher to start the activity
                        startActivityLauncher.launch(intent)
                    }) {
                        ArticleItemSecond(listOf(article))
                    }
                }
            }
        }
    }
}