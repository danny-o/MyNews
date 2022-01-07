package com.smartnews.mynews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smartnews.mynews.R



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor=getColor(R.color.app_theme_color)


        supportFragmentManager.beginTransaction()
            .replace(R.id.container,HomeFragment())
            .commit()
    }

    companion object{
        const val NEWS_ARTICLE="com.smartnews.mynews.news_article"
    }
}