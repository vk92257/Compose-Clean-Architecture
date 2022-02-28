package com.ui.data.mappers

import com.ui.data.data.dto.Comment
import com.ui.data.data.dto.Post

/**
 * @Author: Vivek
 * @Date: 26/02/22
 */

 fun Comment.toCommentForDomain(): com.ui.domain.data.Comment{

    return com.ui.domain.data.Comment(
        body = body,
        id = id,
        name = name,
        email = email,
        postId = postId
    )
}

fun com.ui.domain.data.Comment.toCommentForApi(): Comment{
    return Comment(
        body = body,
        id = id,
        name = name,
        email = email,
        postId = postId
    )
}