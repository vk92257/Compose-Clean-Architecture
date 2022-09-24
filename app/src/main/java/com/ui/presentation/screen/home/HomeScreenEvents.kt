package com.ui.presentation.screen.home

sealed class HomeScreenEvents {
    data class OnSearchEvent(var text: String) : HomeScreenEvents()
    data class OnSaveArticleEvent(var articlePosition: Int) :
        HomeScreenEvents()
    object OpenSavedArticleScreen : HomeScreenEvents()

    data class OnArticleReadEvent(var articlePosition: Int) : HomeScreenEvents()


}


