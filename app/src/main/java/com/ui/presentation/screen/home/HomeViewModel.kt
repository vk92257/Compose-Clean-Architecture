package com.ui.presentation.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
                state = state.copy(
                    articles = mutableStateListOf<Article>().apply {
                        addAll(it.articles)
                    }
                )
            }.onFailure {
//                _UiEvent.send(UiEvent.ShowSnackBar(it.message.toString()))
                newBreezeCache.getArticle().collectLatest {
                    state = state.copy(articles = mutableStateListOf<Article>().apply {
                        addAll(it)
                    })
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
                state = state.copy(searchQuery = action.text)
            }
            is HomeScreenEvents.OnArticleReadEvent -> {
                viewModelScope.launch {
                    _UiEvent.send(UiEvent.ReadArticleClick(state.articles[action.articlePosition]))
                }
            }
            is HomeScreenEvents.OnSaveArticleEvent -> {
                val list = state.articles
                val article = list[action.articlePosition]
                article.isSaved = !article.isSaved
                list[action.articlePosition] = article
                state = state.copy(articles = list)

                viewModelScope.launch(Dispatchers.IO) {
                    if (article.isSaved) newBreezeCache.insertArticle(article)
                    else newBreezeCache.deleteArticle(article.title ?: "")
                }

            }
        }
    }


}



