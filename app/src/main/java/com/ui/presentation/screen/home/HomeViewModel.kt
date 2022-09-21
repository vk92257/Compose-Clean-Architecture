package com.ui.presentation.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ui.data.data.dto.newBreeze.Article
import com.ui.domain.useCases.GetBreakingNewsUseCase
import com.ui.domain.useCases.GetPostsUseCase
import com.ui.navigation.Route
import com.ui.navigation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: Vivek
 * @Date: 27/02/22
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getPostsUseCase: GetPostsUseCase,
    val getBreakingNewsUseCase: GetBreakingNewsUseCase
) : ViewModel() {
    var state by mutableStateOf(emptyList<Article>())
        private set
    private val _UiEvent = Channel<UiEvent>()
    val uiEvent = _UiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            getBreakingNewsUseCase("us", "665f8083e45c4a2b936b2a5030686501").onSuccess {
                state = it.articles
            }.onFailure {
                _UiEvent.send(UiEvent.ShowSnackBar(it.message.toString()))
            }

        }
    }

    fun onNavigateDetail(id: Int) {
        viewModelScope.launch {
            _UiEvent.send(UiEvent.Navigate(Route.DETAILS))
        }
    }
}



