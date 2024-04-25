package com.mobilecomputing.newsapp.screens.news

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


//@Composable
//fun NewsHome(viewModel: NewsViewModel = hiltViewModel()) {
//    // Display the news data
//    DisplayNewsData(viewModel)
//}

//@Composable
//fun NewsHome(viewModel: NewsViewModel = hiltViewModel()) {
//    val newsTypes = listOf("general", "entertainment", "health", "science", "sports", "technology")
//    val selectedTab = remember { mutableStateOf(newsTypes[0]) }
//
//    TabRow(selectedTabIndex = newsTypes.indexOf(selectedTab.value)) {
//        newsTypes.forEach { newsType ->
//            Tab(
//                selected = selectedTab.value == newsType,
//                onClick = {
//                    selectedTab.value = newsType
////                    newsType.value = newsType
//                }
//            ) {
//                Text(text = newsType.capitalize())
//            }
//        }
//    }
//
//    // Display the news data
//    DisplayNewsData(viewModel, selectedTab.value)
//}

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment

@Composable
fun NewsHome(viewModel: NewsViewModel = hiltViewModel()) {
    val newsTypes = listOf("everything","general", "entertainment", "health", "science", "sports", "technology")
    val selectedTab = remember { mutableStateOf(newsTypes[0]) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column() {

            TabRow(selectedTabIndex = newsTypes.indexOf(selectedTab.value), modifier = Modifier.align(Alignment.CenterHorizontally)) {
                newsTypes.forEach { newsType ->
                    Tab(
                        selected = selectedTab.value == newsType,
                        onClick = {
                            selectedTab.value = newsType
                        }
                    ) {
                        Text(text = newsType.capitalize())
                    }
                }
            }

            Text(selectedTab.value.capitalize())
            Spacer(modifier = Modifier.height(16.dp))

            // Display the news data
            DisplayNewsData(viewModel, selectedTab.value)
        }
    }

}

//
//@Composable
//fun DisplayNewsData(viewModel: NewsViewModel, news: String) {
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Button(onClick = {
//            if(newsType.value == "everything") {
//                newsType.value = "top-headlines"
//            } else {
//                newsType.value = "everything"
//            }
////            newsType.value = !newsType.value
//        }) {
//            if (newsType.value == "everything") {
//                Text("Show Everything")
//            } else {
//                Text("Show Top Headlines")
//            }
//        }
//
//        if (viewModel.data.value.loading == true) {
//            CircularProgressIndicator()
//        } else {
//            if (newsType.value == "top-headlines") {
//                Text("Top Headlines")
//            } else {
//                Text("Everything")
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//
//            LazyColumn {
//                items(if (newsType.value=="everything") viewModel.topHeadlineArticlesGeneral.value else viewModel.localNewsArticles.value) { article ->
//                    ArticleItem(article)
//                }
//            }
//        }
//    }
//}

@Composable
fun DisplayNewsData(viewModel: NewsViewModel, news: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        if (viewModel.data.value.loading == true) {
            CircularProgressIndicator()
        } else {
//            Text(news.capitalize())
//            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(getArticlesByNewsType(viewModel, news)) { article ->
                    ArticleItem(article)
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