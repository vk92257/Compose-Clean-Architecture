package com.ui.presentation.screen.saved

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ui.data.data.dto.newBreeze.Article
import com.ui.domain.useCases.ArticleCacheUseCase
import com.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
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
class SavedViewModel @Inject constructor(
    private val newBreezeCache: ArticleCacheUseCase
) : ViewModel() {
    var state by mutableStateOf(SavedState())
        private set
    private val _UiEvent = Channel<UiEvent>()
    val uiEvent = _UiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            newBreezeCache.getArticle().collectLatest {
                state = state.copy(articles = mutableStateListOf<Article>().apply {
                    addAll(it)
                })
            }
        }

    }

    fun onActionPerformed(action: SavedScreenEvents) {
        when (action) {

            is SavedScreenEvents.OnSearchEvent -> {
                state = state.copy(searchQuery = action.text)
            }
            is SavedScreenEvents.OnArticleReadEvent -> {
                viewModelScope.launch {
                    _UiEvent.send(UiEvent.ReadArticleClick(state.articles[action.articlePosition]))
                }
            }
            else -> {}
        }
    }


    fun onBackClick() {
        viewModelScope.launch {
            _UiEvent.send(UiEvent.NavigateUp)
        }
    }

}



