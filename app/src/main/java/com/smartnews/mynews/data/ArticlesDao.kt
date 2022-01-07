package com.smartnews.mynews.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticlesDao {


    @Query("SELECT * from news_articles")
    fun getArticles():LiveData<List<NewsArticle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsArticle(newsArticle: NewsArticle)

    @Update
    suspend fun updateNewsArticle(newsArticle: NewsArticle)

    @Delete
    suspend fun deleteNewsArticle(newsArticle: NewsArticle);
}