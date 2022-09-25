package com.ui.presentation.screen.saved

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ui.R
import com.ui.common.toTime
import com.ui.data.data.dto.newBreeze.Article


@Preview
@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String = "Saved",
    onBackClick: () -> Unit = {}

) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    onBackClick()
                },
            tint = colorResource(id = R.color.black),
            contentDescription = "back"
        )


        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
                .offset(
                    x = (-15).dp
                )
                .align(
                    alignment = Alignment.CenterVertically
                ),
            fontWeight = FontWeight.Normal,
            maxLines = 4,
            color = colorResource(id = R.color.green)
        )


    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ListIte() {
    SavedListItem(
        article = Article(
            id = 0
        )
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SavedListItem(
    article: Article,
    modifier: Modifier = Modifier,
    onItemClicked: () -> Unit = {}
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClicked),
        verticalAlignment = Alignment.CenterVertically
    ) {
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
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(25.dp),
                    color = Color.Transparent
                )
                .clip(
                    shape = RoundedCornerShape(25.dp)
                )
                .padding(3.dp)
                .size(
                    100.dp
                )
                .clickable(onClick = onItemClicked),

            contentDescription = "back"
        )



        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .clickable(onClick = onItemClicked)
        ) {
            Text(
                text = article.title ?: "",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.clickable(onClick = onItemClicked),
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                color = colorResource(id = R.color.green)
            )

            Text(
                text = article.description ?: article.description ?: " ",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.clickable(onClick = onItemClicked),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                color = colorResource(id = R.color.black)
            )



            Text(
                text = "${article.publishedAt?.toTime() ?: " "}- ${article.author ?: " "}",
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(vertical = 3.dp)
                    .clickable(onClick = onItemClicked),
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                color = colorResource(id = R.color.black).copy(
                    alpha = 0.5f
                )
            )


        }


    }
}


@Preview
@Composable
fun StickyHeader(date: String = "Today") {
    Row(
        modifier = Modifier
            .background(
                color = colorResource(
                    id = R.color.background,
                )
            )
            .fillMaxWidth()
            .padding(
                horizontal = 35.dp,
                vertical = 10.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = date.take(10),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Normal
        )


        /*Text(
            text = "See all..",
            style = MaterialTheme.typography.body1,
            color = colorResource(id = com.ui.R.color.green),
            modifier = Modifier.align(
                alignment = Alignment.Bottom
            )
        )*/
    }
}
























