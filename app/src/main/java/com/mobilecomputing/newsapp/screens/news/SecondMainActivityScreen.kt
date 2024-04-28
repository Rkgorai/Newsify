package com.mobilecomputing.newsapp.screens.news

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.mobilecomputing.newsapp.component.ArticleItemPage

@Composable
fun SecondMainActivityScreen(intent: Intent) { // Receive the intent as a parameter
    val context = LocalContext.current // Get the current context
    val activity = context as? Activity // Safe cast to Activity

    // Handle the back button press
    BackHandler {
        activity?.finish()
    }
    Column {
        IconButton(
            onClick = { activity?.finish() }, modifier = Modifier
                .align(Alignment.Start)
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
        }

        Box(
            modifier = Modifier

                .fillMaxSize()
        ) {
            // Add a back button at the top left corner

            val title = intent.getStringExtra("title")
            val content = intent.getStringExtra("content")
            val description = intent.getStringExtra("description")
            val author = intent.getStringExtra("author")
            val url = intent.getStringExtra("url")
            val urlToImage = intent.getStringExtra("urlToImage")
            val publishedAt = intent.getStringExtra("publishedAt")
            val source = intent.getStringExtra("source")

            ArticleItemPage(
                title,
                content,
                description,
                author,
                url,
                urlToImage,
                publishedAt,
                source,
                context
            )
        }
    }

}