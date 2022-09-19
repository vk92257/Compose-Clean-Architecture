package com.ui.data.mappers

import com.ui.data.data.dto.Post

/**
 * @Author: Vivek
 * @Date: 26/02/22
 */

fun Post.toPostForDomain(): com.ui.domain.data.Post {

    return com.ui.domain.data.Post(
        body = body,
        id = id,
        title = title,
        isSaved = false,
        userId = userId,
    )
}

fun com.ui.domain.data.Post.toPostForApi(): Post {
    return Post(
        body = body,
        id = id,
        title = title,
        userId = userId,
    )
}