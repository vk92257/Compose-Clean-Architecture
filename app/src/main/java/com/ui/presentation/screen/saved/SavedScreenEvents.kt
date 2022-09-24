package com.ui.presentation.screen.saved

sealed class SavedScreenEvents {
    data class OnSearchEvent(var text: String) : SavedScreenEvents()

    data class OnArticleReadEvent(var articlePosition: Int) : SavedScreenEvents()


}


