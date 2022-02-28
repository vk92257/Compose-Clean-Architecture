package com.ui.domain.useCases

import com.ui.domain.data.Post
import com.ui.domain.repository.JsonPlaceholderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author: Vivek
 * @Date: 27/02/22
 */
class GetPostsUseCase @Inject constructor(
    private val repository: JsonPlaceholderRepository
) {
    suspend operator fun invoke(): Result<List<Post>> {
        return repository.getPosts()
    }
}