package com.ui.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ui.navigation.UiEvent
import com.ui.presentation.component.Articles


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
    Column(
        modifier = Modifier
            .background(
                color = colorResource(id = com.ui.R.color.background)
            )
            .fillMaxSize()
    ) {

        Spacer(modifier = Modifier.size(35.dp))

        HomeHeader(
            modifier = Modifier.padding(horizontal = 29.dp)
        )
        Spacer(modifier = Modifier.size(20.dp))
        SearchBar(
            modifier = Modifier.padding(horizontal = 25.dp)

        )
        LazyColumn(modifier = Modifier.padding(horizontal = 25.dp)) {
            itemsIndexed(viewModel.state) { index, article ->
                Articles(
                    article = article,
                    onArticleClick = {},
                    onReadClick = {},
                    onSaveClick = {},
                )
            }
        }


    }


}


@Preview
@Composable
fun HomePreview() {
    Home(navigate = {})
}