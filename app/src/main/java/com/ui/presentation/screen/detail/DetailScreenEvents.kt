package com.ui.presentation.screen.detail

import com.ui.data.data.dto.newBreeze.Article

sealed class DetailScreenEvents {
    data class AddArticleToState(val article: Article) : DetailScreenEvents()
    object OnBackClicked : DetailScreenEvents()
    object OnSaveClicked : DetailScreenEvents()

}


