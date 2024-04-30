package com.mobilecomputing.newsapp.screens.news

import android.annotation.SuppressLint
import androidx.compose.foundation.verticalScroll
import android.app.Activity
import android.content.Intent
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.room.Room
import coil.compose.AsyncImage
import com.mobilecomputing.newsapp.R
import com.mobilecomputing.newsapp.component.ArticleItemPage
import com.mobilecomputing.newsapp.database.AppDatabase
import com.mobilecomputing.newsapp.database.ArticleDao
import com.mobilecomputing.newsapp.database.NewsArticle
import kotlinx.coroutines.launch
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SecondMainActivityScreen(intent: Intent) { // Receive the intent as a parameter
//    val context = LocalContext.current // Get the current context
//    val activity = context as? Activity // Safe cast to Activity
////    val urlToImage = intent.getStringExtra("urlToImage")
//    val isFavorite = remember { mutableStateOf(false) }
//    val scope = rememberCoroutineScope()
//    val dao = // get your ArticleDao here
//
//    // Handle the back button press
//    BackHandler {
//        activity?.finish()
//    }
//    Scaffold(
//    topBar = {
//        TopAppBar(
//            title = { Text(text = "") },
//            colors = TopAppBarDefaults.topAppBarColors(
//                containerColor = Color.Transparent,
//            ),
//            navigationIcon =  {
//                IconButton(
//                    onClick = { activity?.finish() },
//                    modifier = Modifier
//                        .padding(7.dp)
//                        .background(Color.White.copy(alpha = 0.6f), shape = CircleShape)
//                        .zIndex(1f)
//                ) {
//                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
//                }
//            },
//            actions = { // Add this block
//                IconButton(
//                    onClick = {
//                        isFavorite.value = !isFavorite.value
//                        isFavorite.value = !isFavorite.value
//                        val article = NewsArticle(
//                            title = title,
//                            content = content,
//                            description = description,
//                            author = author,
//                            url = url,
//                            urlToImage = urlToImage,
//                            publishedAt = publishedAt,
//                            source = source,
//                            isFavorite = isFavorite.value
//                        )
//                        if (isFavorite.value) {
//                            scope.launch { dao.insert(article) }
//                        } else {
//                            scope.launch { dao.delete(article) }
//                        }
//                              /* Handle favorite button click here */ },
//                    modifier = Modifier
//                        .padding(7.dp)
//                        .background(Color.White.copy(alpha = 0.6f), shape = CircleShape)
//                        .zIndex(1f)
//                ) {
//                    if (isFavorite.value) {
//                        Icon(Icons.Filled.Favorite, contentDescription = "Favorite", tint = Color.Black)
//                    } else {
//                        Icon(Icons.Filled.FavoriteBorder, contentDescription = "Favorite", tint = Color.Black)
//                    }
//                }
//            }
//        )
//    }
//)
//    {
//
//        Column {
//
//            Box(
//                modifier = Modifier
//
//                    .fillMaxSize()
//            ) {
//
//                val title = intent.getStringExtra("title")
//                val content = intent.getStringExtra("content")
//                val description = intent.getStringExtra("description")
//                val author = intent.getStringExtra("author")
//                val url = intent.getStringExtra("url")
//                val urlToImage = intent.getStringExtra("urlToImage")
//                val publishedAt = intent.getStringExtra("publishedAt")
//                val source = intent.getStringExtra("source")
//
//                ArticleItemPage(
//                    title,
//                    content,
//                    description,
//                    author,
//                    url,
//                    urlToImage,
//                    publishedAt,
//                    source,
//                    context
//                )
//            }
//        }
//    }
//
//
//    }

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondMainActivityScreen(intent: Intent) { // Receive the intent as a parameter
    val context = LocalContext.current // Get the current context
    val activity = context as? Activity // Safe cast to Activity


    val isFavorite = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).fallbackToDestructiveMigration().build()

    val dao =  db.articleDao() // get your ArticleDao here

    val articleTitle = intent.getStringExtra("title")
    val title = intent.getStringExtra("title")
    val content = intent.getStringExtra("content")
    val description = intent.getStringExtra("description")
    val author = intent.getStringExtra("author")
    val url = intent.getStringExtra("url")
    val urlToImage = intent.getStringExtra("urlToImage")
    val publishedAt = intent.getStringExtra("publishedAt")
    val source = intent.getStringExtra("source")


    LaunchedEffect(articleTitle) {
        val articleInDb = dao.getArticleByTitle(articleTitle.toString())
        isFavorite.value = articleInDb != null
    }

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
                    onClick = {
                        isFavorite.value = !isFavorite.value
                        val article = NewsArticle(
                            title = title.toString(),
                            content = content.toString(),
                            description = description.toString(),
                            author = author.toString(),
                            url = url.toString(),
                            urlToImage = urlToImage.toString(),
                            publishedAt = publishedAt.toString(),
                            source = source.toString(),
                            isFavorite = isFavorite.value
                        )
                        if (isFavorite.value) {
                                scope.launch {
                                    try {
                                        dao.insert(article)
                                    } catch (e: Exception) {
                                        Log.e("DB_INSERT_ERROR", "Error inserting article", e)
                                    }
                                }
                            } else {
                                scope.launch {
                                    try {
                                        dao.deleteByTitle(article.title)
                                    } catch (e: Exception) {
                                        Log.e("DB_DELETE_ERROR", "Error deleting article", e)
                                    }
                                }
                            }
//                        scope.launch {
//                            dao.getFavoriteArticles().collect { articles ->
//                                for (arti in articles) {
//                                    Log.d("DB_DATA", article.toString())
//                                }
//                            }
//                        }

//                        scope.launch {
//                            val count = dao.countArticles()
//                            if (count > 0) {
//                                Log.d("DB_CHECK", "Data is present in the database.$count")
//                            } else {
//                                Log.d("DB_CHECK", "No data is present in the database. $count")
//                            }
//                        }

                    },
                    modifier = Modifier
                        .padding(7.dp)
                        .background(Color.White.copy(alpha = 0.6f), shape = CircleShape)
                        .zIndex(1f)
                ) {
                    if (isFavorite.value) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Favorite", tint = Color.Black)
                    } else {
                        Icon(Icons.Filled.FavoriteBorder, contentDescription = "Favorite", tint = Color.Black)
                    }
                }
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


