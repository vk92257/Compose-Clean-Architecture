package com.ui.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ui.data.data.dto.newBreeze.Article
import com.ui.presentation.component.Articles
import com.ui.util.UiEvent


@Composable

fun Home(
    navigate: (article: Article) -> Unit,
    navigateScreen: (UiEvent.Navigate) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(it.message)
                }
                is UiEvent.Navigate -> {
                    navigateScreen(it)
                }
                is UiEvent.ReadArticleClick -> {
                    navigate(it.article)
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
            modifier = Modifier.padding(horizontal = 29.dp),
            onSavedClick = {
                viewModel.onEvent(HomeScreenEvents.OpenSavedArticleScreen)
            }
        )
        Spacer(modifier = Modifier.size(20.dp))
        SearchBar(
            modifier = Modifier.padding(horizontal = 25.dp),
            onSearchClick = {
                viewModel.onEvent(HomeScreenEvents.OnSearchEvent(it))
            }

        )
        LazyColumn(modifier = Modifier.padding(horizontal = 25.dp)) {
            itemsIndexed(viewModel.state.articles) { index, article ->
                Articles(
                    article = article,
                    onReadClick = {
                        viewModel.onEvent(
                            HomeScreenEvents.OnArticleReadEvent(
                                articlePosition = index,
                            )
                        )
                    },
                    onSaveClick = {
                        viewModel.onEvent(
                            HomeScreenEvents.OnSaveArticleEvent(
                                articlePosition = index,

                                )
                        )
                    },
                )
            }
        }


    }


}


@Preview
@Composable
fun HomePreview() {
//    Home(navigate = {})
}