package com.ui.data.repository

import com.ui.data.data.remote.JsonPlaceholderApi
import com.ui.data.data.dto.newBreeze.NewBreeze
import com.ui.domain.repository.NewBreezeRepository
import javax.inject.Inject

class NewBreezeRepositoryImpl @Inject constructor(
    private val apiRequest: JsonPlaceholderApi
) : NewBreezeRepository {
    override suspend fun getBreakingNews(
        api: String,
        country: String
    ): Result<NewBreeze> {
        return try {

            val response = apiRequest.getNewBreezePosts(country, api)
            if (response.status == "ok") {
                Result.success(response)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}