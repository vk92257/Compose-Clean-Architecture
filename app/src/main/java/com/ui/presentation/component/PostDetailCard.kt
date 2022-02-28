package com.ui.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ui.domain.data.Post
import kotlin.reflect.KFunction1

/**
 * @Author: Vivek
 * @Date: 26/02/22
 */

@Composable
fun PostDetailCard(
    posts: Post,
    onClick: (id: Int)-> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .padding(3.dp)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .background(MaterialTheme.colors.surface)
            .clickable {
                onClick(posts.id)
            }
            .padding(end = 10.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = posts.title,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterVertically)
            )

            Text(
                text = "(${posts.userId})",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Text(
            text = posts.body,
            style = TextStyle(
                color = Color.Black,
                fontSize = 12.sp
            ),
            modifier = Modifier.padding(3.dp)
        )


    }
}