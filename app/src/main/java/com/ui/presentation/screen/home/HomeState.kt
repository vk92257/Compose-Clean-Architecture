package com.ui.presentation.screen.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.ui.data.data.dto.newBreeze.Article

data class HomeState(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    var articles: SnapshotStateList<Article> = mutableStateListOf(),
    var articlesTemp: SnapshotStateList<Article> = mutableStateListOf(),

    )