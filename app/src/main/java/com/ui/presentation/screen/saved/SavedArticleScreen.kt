package com.ui.presentation.screen.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ui.data.data.dto.newBreeze.Article
import com.ui.util.UiEvent
import com.ui.presentation.screen.home.SearchBar
import kotlinx.coroutines.flow.collectLatest


@Composable
fun SavedArticlesScreen(
    viewModel: SavedViewModel = hiltViewModel(),
    navigate: (Article) -> Unit,
    navigateUp: () -> Unit,
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest {
            viewModel.uiEvent.collect {
                when (it) {
                    is UiEvent.ShowSnackBar -> {}
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
                )
        )


        Spacer(modifier = Modifier.size(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 35.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Today",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Normal
            )


            Text(
                text = "See all..",
                style = MaterialTheme.typography.body1,
                color = colorResource(id = com.ui.R.color.green),
                modifier = Modifier.align(
                    alignment = Alignment.Bottom
                )
            )
        }

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
            LazyColumn() {
                itemsIndexed(viewModel.state.articles) { index, item ->
                    SavedListItem(
                        article = item,
                        onItemClicked = {
                            viewModel.onActionPerformed(SavedScreenEvents.OnArticleReadEvent(index))
                        },
                        modifier = Modifier.padding(
                            top = 25.dp,
                            start = 15.dp,
                            end = 15.dp,
                        )
                    )
                }

                item {
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


                }
            }
        }


    }

}