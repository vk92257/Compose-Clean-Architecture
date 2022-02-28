package com.ui.data.repository

import com.ui.data.data.JsonPlaceholderApi
import com.ui.data.mappers.toCommentForDomain
import com.ui.data.mappers.toPostForDomain
import com.ui.domain.data.Comment
import com.ui.domain.data.Post
import com.ui.domain.repository.JsonPlaceholderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

/**
 * @Author: Vivek
 * @Date: 26/02/22
 */
class JsonPlaceholderRepositoryImpl(
    private val api: JsonPlaceholderApi
) : JsonPlaceholderRepository {
    override suspend fun getPosts(): Result<List<Post>> {
        return try {
            val posts =  api.getPosts().map { posts ->
               posts.toPostForDomain()
            }
            Result.success(posts)

        }catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }

    }

    override fun getSinglePost(id: Int): Flow<Post> {
        return api.getSinglePost(id).map { it.toPostForDomain() }
    }

    override fun getComments(id: Int): Flow<List<Comment>> {
        return api.getComments().map { comments ->
            comments.map {
                it.toCommentForDomain()
            }
        }
    }
}