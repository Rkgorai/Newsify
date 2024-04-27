package com.mobilecomputing.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.mobilecomputing.newsapp.component.PageArticleItem
import com.mobilecomputing.newsapp.model.news.Article

class SecondMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent // Get the intent
        setContent {
            SecondMainActivityScreen(intent) // Pass the intent to the composable function
        }
    }
}

@Composable
fun SecondMainActivityScreen(intent: Intent) { // Receive the intent as a parameter
    val title = intent.getStringExtra("title")
    val content = intent.getStringExtra("content")
    val description = intent.getStringExtra("description")
    val author = intent.getStringExtra("author")
    val url = intent.getStringExtra("url")
    val urlToImage = intent.getStringExtra("urlToImage")
    val publishedAt = intent.getStringExtra("publishedAt")
    val source = intent.getStringExtra("source")

    PageArticleItem(title, content, description, author, url, urlToImage, publishedAt, source)



}