package com.ui.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ui.R
import com.ui.domain.data.Post


@Composable
fun ArticleDetailScreen(posts: Post) {
    val imageId: Painter
    val color: Color

    if (posts.isSaved) {
        imageId = painterResource(id = R.drawable.ic_saved)
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

        ){

            item{
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
                                .data(data = R.drawable.temp_two)
                                .crossfade(true)
                                .placeholder(R.drawable.temp_img)
                                .error(R.drawable.temp_img)
                                .fallback(R.drawable.temp_img)
                                .build()
                        ),
                        contentDescription = "bell",
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )

                    Icon(
                        painter = imageId,
                        modifier = Modifier
                            .offset(
                                x = (-15).dp,
                                y = (15).dp
                            )
                            .align(
                                alignment = Alignment.TopEnd
                            )
                            .size(30.dp)
                            .clickable {
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
                            text = posts.time,
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
                            text = posts.title,
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

            item{
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
                    )

                 /*   Column(
                        modifier = Modifier.scrollable(
                            orientation = Orientation.Vertical,
                            state = rememberScrollState()

                        )
                    ) {*/
                        Text(
                            text = posts.body,
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


        /*Top Section*/

     /*   Box(
            modifier = Modifier
                .weight(
                    weight = 1f
                )
                .fillMaxSize()
                .background(
                    color = Color.Black
                )
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = R.drawable.temp_two)
                        .crossfade(true)
                        .placeholder(R.drawable.temp_img)
                        .error(R.drawable.temp_img)
                        .fallback(R.drawable.temp_img)
                        .build()
                ),
                contentDescription = "bell",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds
            )

            Icon(
                painter = imageId,
                modifier = Modifier
                    .offset(
                        x = (-15).dp,
                        y = (15).dp
                    )
                    .align(
                        alignment = Alignment.TopEnd
                    )
                    .size(30.dp)
                    .clickable {
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
                    text = posts.time,
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
                    text = posts.title,
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
*/

        /*Bottom Section */

        /*Column(
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
            )

            Column(
                modifier = Modifier.scrollable(
                    orientation = Orientation.Vertical,
                    state = rememberScrollState()

                )
            ) {
                Text(
                    text = posts.body,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(
                        horizontal = 15.dp
                    ),
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }


        }*/


    }

}


@Preview
@Composable
fun ArticlesDetailsScreenPrev() {
    ArticleDetailScreen(
        posts = Post(
            body = "Washington - Sed ut perspiciatis un natus error sit voluptatem accusan ium doloremque laudan. sed quia consequuntur magni dolores eos ratione voluptatem sequi nesciunt. Neque porro quisqua uia non Sed ut perspiciatis un natus error sit voluptatem accusan ium doloremque laudan. sed quia consequuntur magni dolores eos ratione voluptatem sequi nesciunt. Neque porro quisqua uia non Sed ut perspiciatis un natus error sit voluptatem accusan ium doloremque laudan. sed quia consequuntur magni dolores eos ratione voluptatem sequi nesciunt. Neque porro quisqua uia non Sed ut perspiciatis un natus error sit voluptatem accusan ium doloremque laudan. sed quia consequuntur magni dolores eos ratione voluptatem sequi nesciunt. Neque porro quisqua uia non Sed ut perspiciatis un natus error sit voluptatem accusan ium doloremque laudan. sed quia consequuntur magni dolores eos ratione voluptatem sequi nesciunt. Neque porro quisqua uia non mest, qui dolorem ipsum quia dolo rsit amet, consectetur, adipisci velit sed quia non numquam eius modi empora incidunt ut labore et dolo magnam aliquam quaerat volupta. Ut enim ad minima veniam, quis ostrum exercitationem ullam corri suscipit laboriosam, nisi ut aliqu.",
            id = 1,
            userId = 2,
            isSaved = false,
            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ",
        )
    )
}