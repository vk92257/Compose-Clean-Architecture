package com.ui.domain.repository

import com.ui.data.data.dto.newBreeze.Article
import kotlinx.coroutines.flow.Flow

interface NewsBreezeRoomRepository {
    suspend fun insertArticle(article: Article)
    suspend fun getArticle(): Flow<List<Article>>
    suspend fun deleteArticle(article: String)
}