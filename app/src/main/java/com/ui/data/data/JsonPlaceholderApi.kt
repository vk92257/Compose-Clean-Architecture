package com.ui.data.data

import com.ui.data.data.dto.Comment
import com.ui.data.data.dto.Post
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author: Vivek
 * @Date: 26/02/22
 */
interface JsonPlaceholderApi {
    @GET("posts")
   suspend fun getPosts(): List<Post>

    @GET("posts/1")
    fun getSinglePost(
        @Query("id") id: Int,
    ): Flow<Post>

    @GET("posts/{5}/comments")
    fun getComments(): Flow<List<Comment>>


    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

}

