package com.mobilecomputing.newsapp.screens.news

import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mobilecomputing.newsapp.R
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
            NewScreen()
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
@Composable
fun NewScreen() {
    Card(modifier = Modifier
        .fillMaxSize()
        .padding(40.dp, 100.dp)
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = rememberAsyncImagePainter(model = R.drawable.newicon),
                contentDescription = "Your image description",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape))

            Spacer(modifier = Modifier.padding(20.dp))

            Text(text = "THERE ARE NO FAVOURITE NEWS ARTICLES OF YOURS",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )


        }

    }
}