package com.mobilecomputing.newsapp.screens.news

import android.annotation.SuppressLint
import androidx.compose.foundation.verticalScroll
import android.app.Activity
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.mobilecomputing.newsapp.R
import com.mobilecomputing.newsapp.component.ArticleItemPage
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondMainActivityScreen(intent: Intent) { // Receive the intent as a parameter
    val context = LocalContext.current // Get the current context
    val activity = context as? Activity // Safe cast to Activity
//    val urlToImage = intent.getStringExtra("urlToImage")
    val isFavorite = remember { mutableStateOf(false) }

    // Handle the back button press
    BackHandler {
        activity?.finish()
    }
    Scaffold(
    topBar = {
        TopAppBar(
            title = { Text(text = "") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
            ),
            navigationIcon =  {
                IconButton(
                    onClick = { activity?.finish() },
                    modifier = Modifier
                        .padding(7.dp)
                        .background(Color.White.copy(alpha = 0.6f), shape = CircleShape)
                        .zIndex(1f)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
                }
            },
            actions = { // Add this block
                IconButton(
                    onClick = {isFavorite.value = !isFavorite.value
                              /* Handle favorite button click here */ },
                    modifier = Modifier
                        .padding(7.dp)
                        .background(Color.White.copy(alpha = 0.6f), shape = CircleShape)
                        .zIndex(1f)
                ) {
                    if (isFavorite.value) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Favorite", tint = Color.Black)
                    } else {
                        Icon(Icons.Filled.FavoriteBorder, contentDescription = "Favorite", tint = Color.Black)
                    }                }
            }
        )
    }
)
    {

        Column {

            Box(
                modifier = Modifier

                    .fillMaxSize()
            ) {

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


    }



