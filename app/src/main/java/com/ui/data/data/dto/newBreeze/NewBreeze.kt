package com.ui.data.data.dto.newBreeze

data class NewBreeze(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int,
    val message: String,
    val code: String,
)