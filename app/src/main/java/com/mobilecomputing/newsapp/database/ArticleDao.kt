package com.mobilecomputing.newsapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: NewsArticle)


    @Query("DELETE FROM NewsArticle WHERE title = :title")
    suspend fun deleteByTitle(title: String)


    @Query("SELECT * FROM newsarticle WHERE isFavorite = 1")
    fun getFavoriteArticles(): Flow<List<NewsArticle>>

    @Query("SELECT COUNT(*) FROM NewsArticle")
    suspend fun countArticles(): Int

    @Query("SELECT * FROM NewsArticle")
suspend fun getAllArticles(): List<NewsArticle>

    @Query("SELECT * FROM NewsArticle WHERE title = :title LIMIT 1")
    suspend fun getArticleByTitle(title: String): NewsArticle?
}