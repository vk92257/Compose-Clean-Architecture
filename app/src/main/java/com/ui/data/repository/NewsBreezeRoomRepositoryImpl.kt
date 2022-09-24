package com.ui.data.repository

import com.ui.data.data.dto.newBreeze.Article
import com.ui.data.data.local.ArticleDao
import com.ui.domain.repository.NewsBreezeRoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsBreezeRoomRepositoryImpl @Inject constructor(
    private val dao: ArticleDao
) : NewsBreezeRoomRepository {
    override suspend fun insertArticle(article: Article) = dao.insertArticles(article)
    override suspend fun getArticle(): Flow<List<Article>> = dao.getAll()
    override suspend fun deleteArticle(article: String) = dao.deleteArticle(article)


}
