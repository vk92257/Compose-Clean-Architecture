package com.ui.data.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ui.data.data.dto.newBreeze.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAll(): Flow<List<Article>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertArticles(article: Article)

    @Query("DELETE FROM article WHERE title = :article1")
    fun deleteArticle(article1: String)

}