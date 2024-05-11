package com.mobilecomputing.newsapp.screens.news

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobilecomputing.newsapp.component.ArticleItem
import com.mobilecomputing.newsapp.model.news.Article


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment


import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mobilecomputing.newsapp.SecondMainActivity
import com.mobilecomputing.newsapp.utils.Constant.state_location

//@Composable
//fun NewsHome() {
//    val newsTypes = listOf("for you","general", "entertainment", "health", "science", "sports", "technology")
//    val selectedTab = remember { mutableStateOf(newsTypes[0]) }
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        Column() {
//
//            ScrollableTabRow(
//                selectedTabIndex = newsTypes.indexOf(selectedTab.value),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .align(Alignment.CenterHorizontally)
//            ) {
//                newsTypes.forEach { newsType ->
//                    Tab(
//                        modifier = Modifier.padding(horizontal = 12.dp),
//                        selected = selectedTab.value == newsType,
//                        onClick = {
//                            selectedTab.value = newsType
//                        }
//                    ) {
//                        Text(
//                            text = newsType.toUpperCase(),
//                            modifier = Modifier.padding(8.dp),
//                            fontSize = 20.sp
//                        )
//                    }
//                }
//            }
//
//            // Display the news data
//            DisplayNewsData(selectedTab.value)
//        }
//    }
//}

@Composable
fun NewsHome(viewModel: NewsViewModel = hiltViewModel()) {
    val newsTypes = listOf("for you","general", "entertainment", "health", "science", "sports", "technology")
    val selectedTab = remember { mutableStateOf(newsTypes[0]) }
    val isRefreshing = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing.value),
            onRefresh = {
                isRefreshing.value = true
                // Refresh the news data based on the selected tab
                when(selectedTab.value) {
                    "for you" -> viewModel.getNewsData(false, state_location.value, "in", "general", "publishedAt")
                    "general" -> viewModel.getNewsData(true, state_location.value, "in", "general", "publishedAt")
                    "entertainment" -> viewModel.getNewsData(true, state_location.value, "in", "entertainment", "publishedAt")
                    "health" -> viewModel.getNewsData(true, state_location.value, "in", "health", "publishedAt")
                    "science" -> viewModel.getNewsData(true, state_location.value, "in", "science", "publishedAt")
                    "sports" -> viewModel.getNewsData(true, state_location.value, "in", "sports", "publishedAt")
                    "technology" -> viewModel.getNewsData(true, state_location.value, "in", "technology", "publishedAt")
                }
                isRefreshing.value = false
            }
        ) {
            Column() {
                ScrollableTabRow(
                    selectedTabIndex = newsTypes.indexOf(selectedTab.value),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    newsTypes.forEach { newsType ->
                        Tab(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            selected = selectedTab.value == newsType,
                            onClick = {
                                selectedTab.value = newsType
                            }
                        ) {
                            Text(
                                text = newsType.toUpperCase(),
                                modifier = Modifier.padding(8.dp),
                                fontSize = 20.sp
                            )
                        }
                    }
                }

                // Display the news data
                DisplayNewsData(selectedTab.value)
            }
        }
    }
}

@Composable
fun DisplayNewsData(news: String, viewModel: NewsViewModel=hiltViewModel()) {
    val context = LocalContext.current // Get the current context

    val startActivityLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        // Handle the result of the activity here
    }


    // Call the getNewsData function based on the news type
    LaunchedEffect(key1 = news) {
        when(news) {
            "for you" -> viewModel.getNewsData(false, state_location.value, "in", "general", "publishedAt")
            "general" -> viewModel.getNewsData(true, state_location.value, "in", "general", "publishedAt")
            "entertainment" -> viewModel.getNewsData(true, state_location.value, "in", "entertainment", "publishedAt")
            "health" -> viewModel.getNewsData(true, state_location.value, "in", "health", "publishedAt")
            "science" -> viewModel.getNewsData(true, state_location.value, "in", "science", "publishedAt")
            "sports" -> viewModel.getNewsData(true, state_location.value, "in", "sports", "publishedAt")
            "technology" -> viewModel.getNewsData(true, state_location.value, "in", "technology", "publishedAt")
        }

    }

    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        if (viewModel.data.value.loading == true) {
            CircularProgressIndicator()
        } else {
//            Text(news.capitalize())
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(getArticlesByNewsType(viewModel, news)) { article ->
                    Box(modifier = Modifier.clickable {
                        // Create an Intent to start the new activity
                        val intent = Intent(context, SecondMainActivity::class.java)

                        intent.putExtra("title", article.title)
                        intent.putExtra("author", article.author)
                        intent.putExtra("publishedAt", article.publishedAt)
                        intent.putExtra("source", article.source.name)
                        intent.putExtra("url", article.url)
                        intent.putExtra("urlToImage", article.urlToImage)
                        intent.putExtra("content", article.content)
                        intent.putExtra("description", article.description)
                        // Use the launcher to start the activity
                        startActivityLauncher.launch(intent)
                    }){
                        ArticleItem(article)
                    }
                }
            }
        }
    }
}

fun getArticlesByNewsType(viewModel: NewsViewModel, news: String): List<Article> {
    return when(news) {
        "for you" -> viewModel.localNewsArticles.value
        "general" -> viewModel.topHeadlineArticlesGeneral.value
        "entertainment" -> viewModel.topHeadlineArticlesEntertainment.value
        "health" -> viewModel.topHeadlineArticlesHealth.value
        "science" -> viewModel.topHeadlineArticlesScience.value
        "sports" -> viewModel.topHeadlineArticlesSports.value
        "technology" -> viewModel.topHeadlineArticlesTechnology.value
        "search" -> viewModel.searchedNewsArticles.value
        else -> listOf()
    }
}