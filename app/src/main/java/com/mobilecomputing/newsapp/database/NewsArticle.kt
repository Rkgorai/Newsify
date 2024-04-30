package com.mobilecomputing.newsapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsArticle(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val description: String,
    val author: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val source: String,
    var isFavorite: Boolean = false
)

