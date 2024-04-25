package com.mobilecomputing.newsapp.screens.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobilecomputing.newsapp.component.ArticleItem
import com.mobilecomputing.newsapp.utils.Constant.isTopHeadline


@Composable
fun NewsHome(viewModel: NewsViewModel = hiltViewModel()) {
    // Display the news data
    DisplayNewsData(viewModel)
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

        if (viewModel.data.value.loading == true) {
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