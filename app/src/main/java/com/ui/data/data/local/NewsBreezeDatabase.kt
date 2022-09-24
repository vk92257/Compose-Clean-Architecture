package com.ui.data.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ui.data.data.dto.newBreeze.Article
import com.ui.data.data.dto.newBreeze.Source


@Database(entities = [Article::class], version = 1)
abstract class NewsBreezeDatabase : RoomDatabase() {
    abstract fun newsBreezeDao(): ArticleDao
}