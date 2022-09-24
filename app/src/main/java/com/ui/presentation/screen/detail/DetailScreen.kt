package com.ui.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ui.R
import com.ui.data.data.dto.newBreeze.Article
import com.ui.util.UiEvent
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ArticleDetailScreen(
    article: Article, viewModel: DetailViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    scaffoldState: ScaffoldState
) {
    val imageId: Painter
    val color: Color

    LaunchedEffect(key1 = true) {
        viewModel.onActionPerformed(DetailScreenEvents.AddArticleToState(article))

        viewModel.uiEvent.collectLatest {
            when (it) {

                is UiEvent.NavigateUp -> {
                    navigateUp()
                }
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(it.message)
                }
                else -> {}
            }
        }


    }


    if (viewModel.state.articles.isSaved) {
        imageId = painterResource(id = R.drawable.ic_saved_filled)
        color = colorResource(id = R.color.green)

    } else {
        imageId = painterResource(id = R.drawable.ic_saved)
        color = Color.Gray
    }
    Column(modifier = Modifier.fillMaxSize()) {


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)

        ) {

            item {
                Box(
                    modifier = Modifier
                        .weight(
                            weight = 1f
                        )
                        .fillMaxWidth()
                        .height(350.dp)
                        .background(
                            color = Color.Black
                        )
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = viewModel.state.articles.urlToImage)
                                .crossfade(true)
                                .placeholder(R.drawable.temp_img)
                                .error(R.drawable.temp_img)
                                .fallback(R.drawable.temp_img)
                                .build()
                        ),
                        contentDescription = "bell",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Icon(
                        painter = imageId,
                        modifier = Modifier
                            .offset(
                                x = (-15).dp,
                                y = (15).dp
                            )
                            .background(
                                color = color
                            )
                            .align(
                                alignment = Alignment.TopEnd
                            )
                            .size(30.dp)
                            .clickable {
                                viewModel.onActionPerformed(DetailScreenEvents.OnSaveClicked)
//                                onSavedClick(article)
                            }
                            .padding(
                                vertical = 5.dp
                            ),
                        tint = colorResource(id = R.color.white),
                        contentDescription = "filter"
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        modifier = Modifier
                            .offset(
                                x = (15).dp,
                                y = (15).dp
                            )
                            .align(
                                alignment = Alignment.TopStart
                            )
                            .size(30.dp)
                            .clickable {

                                viewModel.onActionPerformed(DetailScreenEvents.OnBackClicked)
//                                onBackClick()
                            }
                            .padding(
                                vertical = 3.dp
                            ),
                        tint = colorResource(id = R.color.white),
                        contentDescription = "filter"
                    )

                    Column(
                        modifier = Modifier.align(
                            alignment = Alignment.BottomStart
                        )
                    ) {

                        Text(
                            text = viewModel.state.articles.publishedAt ?: "",
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(
                                horizontal = 15.dp
                            ),
                            fontWeight = FontWeight.Normal,
                            maxLines = 4,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = viewModel.state.articles.title ?: "",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(
                                horizontal = 15.dp
                            ),
                            maxLines = 2,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.size(65.dp))
                    }


                }

            }

            item {
                Column(
                    modifier = Modifier
                        .weight(
                            weight = 1f
                        )
                        .offset(
                            y = (-40).dp
                        )
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(
                                topStart = 25.dp,
                                topEnd = 25.dp
                            )
                        )


                ) {

                    ReporterDetails(
                        modifier = Modifier.padding(
                            horizontal = 15.dp,
                            vertical = 30.dp
                        ),
                        isSaved = viewModel.state.articles.isSaved,
                        reporterName = viewModel.state.articles.source.name,
                        reporterImage = viewModel.state.articles.urlToImage ?: "",
                        mediaGroup = viewModel.state.articles.source.name,
                        onSaveClick = {
//                            onSavedClick()
                            viewModel.onActionPerformed(DetailScreenEvents.OnSaveClicked)

                        }
                    )

                    Text(
                        text = viewModel.state.articles.content
                            ?: viewModel.state.articles.description ?: "",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(
                            horizontal = 15.dp
                        ),
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
//                    }


                }
            }
        }
    }

}
