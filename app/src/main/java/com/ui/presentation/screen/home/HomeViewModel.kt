package com.ui.presentation.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ui.domain.data.Post
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
    val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    var state by mutableStateOf(emptyList<Post>())
        private set
    private val _UiEvent = Channel<UiEvent>()
    val uiEvent = _UiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            state = emptyList()
            getPostsUseCase().onSuccess {
                state = it
            }.onFailure {
                state = emptyList()
            }
        }
    }

    fun onNavigateDetail(id: Int) {
        viewModelScope.launch {
            _UiEvent.send(UiEvent.Navigate(Route.DETAILS))
        }
    }
}



