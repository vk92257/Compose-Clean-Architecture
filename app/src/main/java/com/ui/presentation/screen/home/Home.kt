package com.ui.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ui.navigation.UiEvent
import com.ui.presentation.screen.detail.ArticlesDetailsScreenPrev


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
/*

        Spacer(modifier = Modifier.size(35.dp))

        HomeHeader(
            modifier = Modifier.padding(horizontal = 29.dp)
        )
        Spacer(modifier = Modifier.size(20.dp))
        SearchBar(
            modifier = Modifier.padding(horizontal = 25.dp)

        )



    LazyColumn(modifier = Modifier.padding(horizontal = 25.dp)) {
        items(5) {
            Articles(posts = Post(
                body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                id = 1,
                userId = 2,
                isSaved = true,
                title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ",
            ), onArticleClick = {}, onSaveClick = {}, onReadClick = {})
        }


    }*/


        ArticlesDetailsScreenPrev()
    }


}


@Preview
@Composable
fun HomePreview() {
    Home(navigate = {})
}