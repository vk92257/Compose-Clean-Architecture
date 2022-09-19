package com.ui.presentation.screen.saved

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
import com.ui.domain.data.Post


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


@Preview
@Composable
fun ListIte() {
    SavedListItem(
        Post = Post(
            body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            id = 1,
            userId = 2,
            isSaved = true,
            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ",
        )
    )
}


@Composable
fun SavedListItem(
    Post: Post,
    modifier: Modifier = Modifier,
) {


    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
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
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(25.dp),
                    color = Color.Transparent
                ).clip(
                    shape = RoundedCornerShape(25.dp)
                )
                .padding(3.dp)
                .size(
                    100.dp
                )
                .clickable {
                },

            contentDescription = "back"
        )



        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(
                text = Post.title,
                style = MaterialTheme.typography.body1,
                modifier = Modifier,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                color = colorResource(id = R.color.green)
            )

            Text(
                text = Post.body,
                style = MaterialTheme.typography.body1,
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                color = colorResource(id = R.color.black)
            )



            Text(
                text = "${Post.time}- James Adam",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(vertical = 3.dp),
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                color = colorResource(id = R.color.black).copy(
                    alpha = 0.5f
                )
            )


        }


    }
}


























