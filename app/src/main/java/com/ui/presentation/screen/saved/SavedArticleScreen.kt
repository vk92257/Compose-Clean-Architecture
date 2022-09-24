package com.ui.presentation.screen.saved

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ui.data.data.dto.newBreeze.Article
import com.ui.presentation.screen.home.SearchBar
import com.ui.util.UiEvent
import kotlinx.coroutines.flow.collectLatest


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SavedArticlesScreen(
    viewModel: SavedViewModel = hiltViewModel(),
    navigate: (Article) -> Unit,
    navigateUp: () -> Unit,
    scaffoldState: ScaffoldState
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest {
            viewModel.uiEvent.collect {
                when (it) {
                    is UiEvent.ShowSnackBar -> {
                        scaffoldState.snackbarHostState.showSnackbar(it.message)
                    }
                    is UiEvent.ReadArticleClick -> {
                        navigate(it.article)
                    }
                    is UiEvent.NavigateUp -> {
                        navigateUp()
                    }
                    else -> {}
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = com.ui.R.color.background)
            )
    ) {

        Spacer(modifier = Modifier.size(30.dp))
        Toolbar(
            modifier = Modifier
                .padding(
                    horizontal = 30.dp
                ),
            onBackClick = viewModel::onBackClick
        )


        Spacer(modifier = Modifier.size(30.dp))
        SearchBar(
            modifier = Modifier
                .padding(
                    horizontal = 30.dp
                )
                .shadow(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(22.dp)
                ),
            onSearchClick = {
                viewModel.onActionPerformed(SavedScreenEvents.OnSearchEvent(it))
            }
        )


        Spacer(modifier = Modifier.size(30.dp))


        Box(
            modifier = Modifier
                .align(
                    alignment = Alignment.CenterHorizontally
                )
                .padding(
                    20.dp
                )
                .fillMaxWidth()
                .shadow(2.dp, RoundedCornerShape(10.dp))
                .background(
                    color = colorResource(id = com.ui.R.color.white),
                    shape = RoundedCornerShape(
                        10.dp
                    )
                ),

            ) {
            LazyColumn(state = rememberLazyListState()) {
                viewModel.state.groupedByDate.forEach { (date, articleForDate) ->
                    stickyHeader {
                        StickyHeader(date)
                    }

                    itemsIndexed(articleForDate) { index, item ->
                        SavedListItem(
                            article = item,
                            onItemClicked = {
                                viewModel.onActionPerformed(
                                    SavedScreenEvents.OnArticleReadEvent(
                                        index
                                    )
                                )
                            },
                            modifier = Modifier.padding(
                                top = 25.dp,
                                start = 15.dp,
                                end = 15.dp,
                            )
                        )
                    }

                }


                /* item {
                     Spacer(modifier = Modifier.size(20.dp))
                     Column(modifier = Modifier.fillMaxWidth()) {
                         Icon(
                             imageVector = Icons.Default.ArrowDropDown,
                             contentDescription = "Down",
                             tint = colorResource(id = com.ui.R.color.green),
                             modifier = Modifier
                                 .align(
                                     alignment = Alignment.CenterHorizontally
                                 )
                                 .size(
                                     60.dp
                                 )
                         )
                     }


                 }*/
            }
        }


    }

}