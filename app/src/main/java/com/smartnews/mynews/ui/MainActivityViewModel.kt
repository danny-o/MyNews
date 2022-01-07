package com.smartnews.mynews.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


import com.smartnews.mynews.data.NewsArticle
import com.smartnews.mynews.data.Repository



class MainActivityViewModel (val newsApplication: Application):AndroidViewModel(newsApplication) {


    val repository:Repository=Repository(newsApplication)

    fun getArticles(): LiveData<List<NewsArticle>> {

        return repository.getArticles()

    }

    suspend fun insertNewsArticle(newsArticle: NewsArticle){
        repository.insertNewsArticle(newsArticle)
    }

    suspend fun deleteNewsArticle(newsArticle: NewsArticle){
        repository.deleteArticle(newsArticle)
    }

    suspend fun updateNewsArticle(newsArticle: NewsArticle){
        repository.updateNewsArticle(newsArticle)
    }
}