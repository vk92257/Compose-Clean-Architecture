package com.ui.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ui.R


@Preview
@Composable
fun ReporterDetails(
    modifier: Modifier = Modifier,
    isSaved: Boolean = false,
    reporterName: String = "James Adams",
    mediaGroup: String = "Loren Correspondent",
    onSaveClick: () -> Unit = {},
    reporterImage: String = ""
) {


    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = reporterImage)
                        .crossfade(true)
                        .placeholder(R.drawable.temp_img)
                        .error(R.drawable.temp_img)
                        .fallback(R.drawable.temp_img)
                        .build()
                ),
                contentDescription = "bell",
                modifier = Modifier
                    .size(50.dp),
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column {
                Text(
                    text = reporterName.takeLast(40) ?: " ",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(
                        start = 12.dp,
                        end = 12.dp
                    ),
                    maxLines = 1,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = mediaGroup.takeLast(40),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(
                        start = 12.dp,
                        end = 12.dp
                    ),
                    maxLines = 1,
                    color = Color.Gray,
                    fontWeight = FontWeight.Light
                )

            }
        }


        Button(
            onClick = {
                onSaveClick()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.green)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = if (isSaved) {
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