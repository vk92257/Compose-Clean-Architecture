package com.ui.domain.repository

import com.ui.data.data.dto.newBreeze.NewBreeze

interface NewBreezeRepository {
    suspend fun getBreakingNews(
        api: String,
        country: String
    ): Result<NewBreeze>

}