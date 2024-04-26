package com.mobilecomputing.newsapp.screens.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobilecomputing.newsapp.component.ArticleItem
import com.mobilecomputing.newsapp.model.news.Article
import com.mobilecomputing.newsapp.utils.Constant.newsType


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment


import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobilecomputing.newsapp.utils.Constant.state_location


//@Preview
@Composable
fun NewsHome(navController: NavController) {
    val newsTypes = listOf("everything","general", "entertainment", "health", "science", "sports", "technology")
    val selectedTab = remember { mutableStateOf(newsTypes[0]) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column() {

            ScrollableTabRow(
                selectedTabIndex = newsTypes.indexOf(selectedTab.value),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp)
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
            DisplayNewsData(selectedTab.value, navController = navController)
        }
    }
}

@Composable
fun DisplayNewsData(news: String, viewModel: NewsViewModel=hiltViewModel()) {

    // Call the getNewsData function based on the news type
    LaunchedEffect(key1 = news) {
        when(news) {
            "everything" -> viewModel.getNewsData(false, state_location.value, "in", "general", "publishedAt")
            "general" -> viewModel.getNewsData(true, state_location.value, "in", "general", "publishedAt")
            "entertainment" -> viewModel.getNewsData(true, state_location.value, "in", "entertainment", "publishedAt")
            "health" -> viewModel.getNewsData(true, state_location.value, "in", "health", "publishedAt")
            "science" -> viewModel.getNewsData(true, state_location.value, "in", "science", "publishedAt")
            "sports" -> viewModel.getNewsData(true, state_location.value, "in", "sports", "publishedAt")
            "technology" -> viewModel.getNewsData(true, state_location.value, "in", "technology", "publishedAt")
        }

    }
    Column(modifier = Modifier.padding(16.dp)) {
        if (viewModel.data.value.loading == true) {
            CircularProgressIndicator()
        } else {
            Text(news.capitalize())
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(getArticlesByNewsType(viewModel, news)) { article ->
                    Box(modifier = Modifier.clickable {

                        navController.navigate("detail/${article.title}")
                    }) {
                        ArticleItem(article)
                    }
                }
            }
        }
    }
}

fun getArticlesByNewsType(viewModel: NewsViewModel, news: String): List<Article> {
    return when(news) {
        "everything" -> viewModel.localNewsArticles.value
        "general" -> viewModel.topHeadlineArticlesGeneral.value
        "entertainment" -> viewModel.topHeadlineArticlesEntertainment.value
        "health" -> viewModel.topHeadlineArticlesHealth.value
        "science" -> viewModel.topHeadlineArticlesScience.value
        "sports" -> viewModel.topHeadlineArticlesSports.value
        "technology" -> viewModel.topHeadlineArticlesTechnology.value
        else -> listOf()
    }
}