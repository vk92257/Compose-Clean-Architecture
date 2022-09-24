package com.ui.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ui.R

@Preview
@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    onSavedClick: () -> Unit = {},
) {

    Box(
        modifier = modifier
            .fillMaxWidth(),

        ) {
        Icon(
            painter = painterResource(id = R.drawable.text), contentDescription = "newsBreeza21",
            modifier = Modifier.align(
                alignment = Alignment.CenterStart
            )
        )


        Icon(
            painter = painterResource(id = R.drawable.ic_saved),
            modifier = Modifier

                .align(
                    alignment = Alignment.TopEnd
                )
                .size(40.dp)
                .background(
                    color = colorResource(id = R.color.green), shape = RoundedCornerShape(5.dp)
                )
                .padding(10.dp)
                .clickable {
                    onSavedClick()
                },
            tint = colorResource(id = R.color.white),
            contentDescription = "filter"
        )


    }


}


@Preview
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearchClick: (String) -> Unit = {},
) {
    var state by remember { mutableStateOf("") }
    val leadingIconView = @Composable {
        IconButton(
            onClick = {

            },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                modifier = Modifier
                    .size(20.dp)
                    .alpha(0.5f)
            )
        }
    }

    val trailingIconView = @Composable {
        IconButton(
            onClick = {

            },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "filter",
                modifier = Modifier
                    .size(20.dp)
                    .alpha(0.5f)
            )
        }
    }


    TextField(
        value = state,

        onValueChange = {
            state = it
            onSearchClick(state)
        },
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(25.dp),
            )
            .height(60.dp),
        trailingIcon = trailingIconView,
        leadingIcon = leadingIconView,

        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = "Search",
                textAlign = TextAlign.Center,
                color = Color.Gray,
            )
        },
        shape = RoundedCornerShape(25.dp),
    )


}


