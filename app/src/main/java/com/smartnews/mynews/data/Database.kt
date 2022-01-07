package com.smartnews.mynews.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(entities = [NewsArticle::class],version = 1,exportSchema = false)
abstract class Database : RoomDatabase(){


    abstract fun articlesDao(): ArticlesDao


    companion object {

        const val DATABASE_NAME="articles.db"
        @Volatile
        private var INSTANCE: Database? = null

        fun getInstance(context: Context): Database =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                Database::class.java, DATABASE_NAME
            )
                .build()
    }
}