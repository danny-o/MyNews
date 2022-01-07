package com.smartnews.mynews.data

import android.content.Context
import androidx.lifecycle.LiveData


class Repository (context: Context){



    var database=Database.getInstance(context)




    fun getArticles(): LiveData<List<NewsArticle>> {

        return database.articlesDao().getArticles()

    }

    suspend fun deleteArticle(newsArticle: NewsArticle) {

        database.articlesDao().deleteNewsArticle(newsArticle)
    }

    suspend fun insertNewsArticle(newsArticle: NewsArticle){
        database.articlesDao().insertNewsArticle(newsArticle)
    }

    suspend fun updateNewsArticle(newsArticle: NewsArticle){
        database.articlesDao().updateNewsArticle(newsArticle)
    }


}