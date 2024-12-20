package com.mobilecomputing.newsapp.screens.news

import android.annotation.SuppressLint
import androidx.compose.foundation.verticalScroll
import android.app.Activity
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.ThumbUp
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
import androidx.compose.ui.graphics.ColorFilter
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
import java.util.Locale


//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SecondMainActivityScreen(intent: Intent) { // Receive the intent as a parameter
//    lateinit var tts: TextToSpeech
//    val context = LocalContext.current // Get the current context
//    val activity = context as? Activity // Safe cast to Activity
//
//
//    val isFavorite = remember { mutableStateOf(false) }
//    val scope = rememberCoroutineScope()
//    val db = Room.databaseBuilder(
//        context,
//        AppDatabase::class.java, "database-name"
//    ).fallbackToDestructiveMigration().build()
//
//    val dao =  db.articleDao() // get your ArticleDao here
//
//    val articleTitle = intent.getStringExtra("title")
//    val title = intent.getStringExtra("title")
//    val content = intent.getStringExtra("content")
//    val description = intent.getStringExtra("description")
//    val author = intent.getStringExtra("author")
//    val url = intent.getStringExtra("url")
//    val urlToImage = intent.getStringExtra("urlToImage")
//    val publishedAt = intent.getStringExtra("publishedAt")
//    val source = intent.getStringExtra("source")
//
//
//    LaunchedEffect(articleTitle) {
//        val articleInDb = dao.getArticleByTitle(articleTitle.toString())
//        isFavorite.value = articleInDb != null
//    }
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
//                        val article = NewsArticle(
//                            title = title.toString(),
//                            content = content.toString(),
//                            description = description.toString(),
//                            author = author.toString(),
//                            url = url.toString(),
//                            urlToImage = urlToImage.toString(),
//                            publishedAt = publishedAt.toString(),
//                            source = source.toString(),
//                            isFavorite = isFavorite.value
//                        )
//                        if (isFavorite.value) {
//                                scope.launch {
//                                    try {
//                                        dao.insert(article)
//                                    } catch (e: Exception) {
//                                        Log.e("DB_INSERT_ERROR", "Error inserting article", e)
//                                    }
//                                }
//                            } else {
//                                scope.launch {
//                                    try {
//                                        dao.deleteByTitle(article.title)
//                                    } catch (e: Exception) {
//                                        Log.e("DB_DELETE_ERROR", "Error deleting article", e)
//                                    }
//                                }
//                            }
//
//                    },
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
//                IconButton(
//                    onClick = {
//                        val sendIntent: Intent = Intent().apply {
//                            action = Intent.ACTION_SEND
//                            putExtra(Intent.EXTRA_TEXT, url)
//                            type = "text/plain"
//                        }
//
//                        val shareIntent = Intent.createChooser(sendIntent, null)
//                        context.startActivity(shareIntent)
//                    },
//                    modifier = Modifier
//                        .padding(7.dp)
//                        .background(Color.White.copy(alpha = 0.6f), shape = CircleShape)
//                        .zIndex(1f)
//                ) {
//                    Icon(Icons.Filled.Share, contentDescription = "Share", tint = Color.Black)
//                }
//
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
//}

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

    // Initialize TextToSpeech
    val ttsState = remember { mutableStateOf<TextToSpeech?>(null) }

    ttsState.value = TextToSpeech(context) { status ->
        if (status != TextToSpeech.ERROR) {
            ttsState.value?.language = Locale.US
        }
    }

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

                // Add IconButton for TextToSpeech
                val isSpeaking = remember { mutableStateOf(false) }

                IconButton(
                    onClick = {
                        val fullText = "$title. $description. $content"
                        if (isSpeaking.value) {
                            ttsState.value?.stop()
                            isSpeaking.value = false
                        } else {
                            ttsState.value?.speak(fullText, TextToSpeech.QUEUE_FLUSH, null, "")
                            isSpeaking.value = true
                        }
                    },
                    modifier = Modifier
                        .padding(7.dp)
                        .background(Color.White.copy(alpha = 0.6f), shape = CircleShape)
                        .zIndex(1f)
                ) {
                    if (isSpeaking.value) {
                        Box {
                            Image(
                                painter = painterResource(id = R.drawable.pause_two),
                                contentDescription = "Stop",
                                modifier = Modifier.fillMaxSize()
                                    .background(Color.White.copy(alpha = 0.6f))
                            )
                            Box(
                                modifier = Modifier

                            )
                        }

                    } else {
                        Icon(Icons.Filled.PlayArrow,
                            contentDescription = "Speak",
                            tint = Color.Black
                        )

                    }
                }

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
                IconButton(
                    onClick = {
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, url)
                            type = "text/plain"
                        }

                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    },
                    modifier = Modifier
                        .padding(7.dp)
                        .background(Color.White.copy(alpha = 0.6f), shape = CircleShape)
                        .zIndex(1f)
                ) {
                    Icon(Icons.Filled.Share, contentDescription = "Share", tint = Color.Black)
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

