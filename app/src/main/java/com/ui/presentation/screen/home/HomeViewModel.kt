package com.ui.presentation.screen.home

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ui.data.data.dto.newBreeze.Article
import com.ui.domain.useCases.ArticleCacheUseCase
import com.ui.domain.useCases.GetBreakingNewsUseCase
import com.ui.navigation.Route
import com.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: Vivek
 * @Date: 27/02/22
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getBreakingNewsUseCase: GetBreakingNewsUseCase,
    private val newBreezeCache: ArticleCacheUseCase
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    private val _UiEvent = Channel<UiEvent>()
    val uiEvent = _UiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            getBreakingNewsUseCase("us", "665f8083e45c4a2b936b2a5030686501").onSuccess {

                val list = mutableStateListOf<Article>().apply {
                    addAll(it.articles)
                }
                state = state.copy(
                    articles = list
                )
                state.articlesTemp = list
            }.onFailure {
                newBreezeCache.getArticle().collectLatest {
                    val list = mutableStateListOf<Article>().apply {
                        addAll(it)
                    }
                    state = state.copy(articles = list)
                    state.articlesTemp = list
                }
            }

        }
    }

    fun onEvent(action: HomeScreenEvents) {
        when (action) {
            is HomeScreenEvents.OpenSavedArticleScreen -> {
                viewModelScope.launch {
                    _UiEvent.send(UiEvent.Navigate(Route.SAVED))
                }
            }

            is HomeScreenEvents.OnSearchEvent -> {
                state.articlesTemp.filter { article ->
                    article.title.contains(action.text, true) || article.description?.contains(
                        action.text,
                        true
                    ) ?: false
                }.let {
                    state = state.copy(articles = it.toMutableStateList())
                }
            }
            is HomeScreenEvents.OnArticleReadEvent -> {
                viewModelScope.launch {
                    _UiEvent.send(UiEvent.ReadArticleClick(state.articles[action.articlePosition]))
                }
            }
            is HomeScreenEvents.OnSaveArticleEvent -> {
                val list = state.articles.toMutableStateList()
                val article = list[action.articlePosition]
                article.isSaved = !article.isSaved
                list[action.articlePosition] = article
                state = state.copy(articles = list)

                viewModelScope.launch(Dispatchers.IO) {
                    if (article.isSaved) {
                        newBreezeCache.insertArticle(article)
                        UiEvent.ShowSnackBar("Article Saved")
                    } else {
                        newBreezeCache.deleteArticle(article.title ?: "")
                        UiEvent.ShowSnackBar("Article Removed ")
                    }
                }

            }
        }
    }


}



