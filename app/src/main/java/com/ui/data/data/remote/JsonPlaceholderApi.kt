package com.ui.data.data.remote

import com.ui.data.data.dto.Comment
import com.ui.data.data.dto.Post
import com.ui.data.data.dto.newBreeze.NewBreeze
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


    @GET("top-headlines")
    suspend fun getNewBreezePosts(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): NewBreeze


    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }

}

