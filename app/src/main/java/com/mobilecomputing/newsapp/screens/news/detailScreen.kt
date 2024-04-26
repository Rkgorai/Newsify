package com.mobilecomputing.newsapp.screens.news

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, articleId: String?) {
    val viewModel: NewsViewModel = hiltViewModel()
    val article = articleId?.let { viewModel.getArticleById(it) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Screen") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(top = 80.dp , start = 25.dp, end = 25.dp),
                elevation = CardDefaults.elevatedCardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(25
                    .dp)) {
                    Text(text = article?.title ?: "", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Author: ${article?.author}", fontSize = 14.sp)
                    Text(text = "Published At: ${article?.publishedAt}", fontSize = 14.sp)
                    Text(text = "Source: ${article?.source?.name}", fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Description: ${article?.content}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
 }
    )
}