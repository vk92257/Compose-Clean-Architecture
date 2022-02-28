package com.ui.domain.repository

import com.ui.domain.data.Comment
import com.ui.domain.data.Post
import kotlinx.coroutines.flow.Flow


/**
 * @Author: Vivek
 * @Date: 26/02/22
 */
interface JsonPlaceholderRepository {

 suspend fun  getPosts(): Result<List<Post>>
    fun getSinglePost(id: Int): Flow<Post>
    fun getComments(id: Int): Flow<List<Comment>>

}