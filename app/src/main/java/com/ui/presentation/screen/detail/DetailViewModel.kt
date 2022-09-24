package com.ui.presentation.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ui.domain.useCases.ArticleCacheUseCase
import com.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: Vivek
 * @Date: 27/02/22
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newBreezeCache: ArticleCacheUseCase
) : ViewModel() {
    var state by mutableStateOf(DetailState())
        private set
    private val _UiEvent = Channel<UiEvent>()
    val uiEvent = _UiEvent.receiveAsFlow()


    fun onActionPerformed(action: DetailScreenEvents) {
        when (action) {
            is DetailScreenEvents.AddArticleToState -> {
                state = state.copy(
                    articles = action.article
                )
            }
            is DetailScreenEvents.OnBackClicked -> {
                viewModelScope.launch {
                    _UiEvent.send(UiEvent.NavigateUp)
                }

            }
            is DetailScreenEvents.OnSaveClicked -> {
                state = state.copy(
                    articles = state.articles.copy(isSaved = !state.articles.isSaved)
                )

                viewModelScope.launch(Dispatchers.IO) {
                    if (state.articles.isSaved) {
                        newBreezeCache.insertArticle(state.articles)
                        UiEvent.ShowSnackBar("Article Saved")

                    } else {
                        newBreezeCache.deleteArticle(state.articles.title ?: "")
                        UiEvent.ShowSnackBar("Article Removed ")
                    }
                }
            }
        }
    }


}



