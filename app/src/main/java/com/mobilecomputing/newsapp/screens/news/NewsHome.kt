package com.mobilecomputing.newsapp.screens.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobilecomputing.newsapp.model.news.Article
import com.mobilecomputing.newsapp.utils.Constants.isTopHeadline


@Composable
fun NewsHome(viewModel: NewsViewModel = hiltViewModel()) {
    // Display the news data
    DisplayNewsData(viewModel)
}

@Composable
fun ArticleItem(article: Article) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = article.title ?: "", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Author: ${article.author}", fontSize = 14.sp)
            Text(text = "Published At: ${article.publishedAt}", fontSize = 14.sp)
            Text(text = "Source: ${article.source.name}", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Description: ${article.description}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun DisplayNewsData(viewModel: NewsViewModel) {

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { isTopHeadline.value = !isTopHeadline.value }) {
            if (isTopHeadline.value) {
                Text("Show Everything")
            } else {
                Text("Show Top Headlines")
            }
        }

        if(viewModel.data.value.loading == true) {
            CircularProgressIndicator()
        } else {
            if (isTopHeadline.value) {
                Text("Top Headlines")
            } else {
                Text("Everything")
            }
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(if (isTopHeadline.value) viewModel.topHeadlineArticles.value else viewModel.everythingArticles.value) { article ->
                    ArticleItem(article)
                }
            }
        }
    }
}