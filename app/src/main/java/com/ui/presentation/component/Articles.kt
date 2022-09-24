package com.ui.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.ui.data.data.dto.newBreeze.Article

/**
 * @Author: Vivek
 * @Date: 26/02/22
 */

@Composable
fun Articles(
    article: Article,
    onSaveClick: () -> Unit,
    onReadClick: () -> Unit,
) {


    val imageId: Painter
    val color: Color

    if (article.isSaved) {
        imageId = painterResource(id = R.drawable.ic_saved_filled)
        color = colorResource(id = R.color.green)

    } else {
        imageId = painterResource(id = R.drawable.ic_saved)
        color = Color.Gray
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onReadClick() }
    ) {

        Spacer(modifier = Modifier.size(20.dp))
        Divider(
            color = colorResource(id = R.color.divider_color),
            thickness = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .background(
                    shape = RoundedCornerShape(8.dp),
                    color = colorResource(id = R.color.divider_color)
                )

        )

        Spacer(modifier = Modifier.size(20.dp))
        Box {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = article.urlToImage)
                        .crossfade(true)
                        .placeholder(R.drawable.temp_img)
                        .error(R.drawable.temp_img)
                        .fallback(R.drawable.temp_img)
                        .build()
                ),
                contentDescription = "bell",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(12.dp)),
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
                    .background(
                        color = color, shape = RoundedCornerShape(5.dp)
                    )
                    .clickable {
                        onSaveClick()
                    }
                    .padding(
                        vertical = 5.dp
                    ),
                tint = colorResource(id = R.color.white),
                contentDescription = "filter"
            )

        }




        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = article.title ?: " ",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(
                start = 12.dp,
                end = 12.dp
            ),
            maxLines = 2,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = article.description ?: "",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(
                start = 12.dp,
                end = 12.dp
            ),
            fontWeight = FontWeight.Normal,
            maxLines = 4,
            color = Color.Black
        )


        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = article.publishedAt ?: "",
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(
                start = 12.dp,
                end = 12.dp
            ),
            fontWeight = FontWeight.Normal,
            maxLines = 4,
            color = Color.Gray
        )



        Row(
            modifier = Modifier
                .align(
                    alignment = Alignment.CenterHorizontally
                )
                .padding(
                    vertical = 20.dp
                )
        ) {
            Button(
                onClick = { onReadClick() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.green)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Read",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }


            Spacer(modifier = Modifier.width(28.dp))

            Button(
                onClick = { onSaveClick() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.green)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = if (article.isSaved) {
                        "Remove"
                    } else {
                        "Save"
                    },
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }


        }


    }


}


@Preview
@Composable
fun ArticlesPrev() {
    /*   Articles(article = Post(
           body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
           id = 1,
           userId = 2,
           isSaved = true,
           title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ",
       ), onArticleClick = {}, onSaveClick = {}, onReadClick = {})*/
}