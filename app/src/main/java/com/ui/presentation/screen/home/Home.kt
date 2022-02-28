package com.ui.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ui.navigation.UiEvent
import com.ui.presentation.component.PostDetailCard
import com.ui.presentation.screen.home.HomeViewModel
import kotlinx.coroutines.flow.collect


@Composable
fun Home(
    navigate: (UiEvent.Navigate) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is UiEvent.ShowSnackBar -> {}
                is UiEvent.Navigate -> {
                    navigate(it)
                }
                else -> {}
            }
        }
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(viewModel.state.size) {
            PostDetailCard(viewModel.state[it] ,viewModel:: onNavigateDetail)
        }

    }


}