package com.ui.domain.useCases

import com.ui.data.data.dto.newBreeze.Article
import com.ui.domain.repository.NewsBreezeRoomRepository
import javax.inject.Inject

class ArticleCacheUseCase @Inject constructor(
    private val articleRepository: NewsBreezeRoomRepository
) {
    suspend fun insertArticle(article: Article) = articleRepository.insertArticle(article)

    suspend fun getArticle() = articleRepository.getArticle()

    suspend fun deleteArticle(article: String) = articleRepository.deleteArticle(article)

}