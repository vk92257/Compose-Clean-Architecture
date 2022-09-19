package com.ui.domain.data

data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    val isSaved : Boolean ,
    var time : String = "29-09-21"
)