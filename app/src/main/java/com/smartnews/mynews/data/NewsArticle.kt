package com.smartnews.mynews.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "news_articles")
data class NewsArticle(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id:Long?=null,

    @NonNull
    var title:String,

    @NonNull
    var date: String,


    var content:String?
): Parcelable