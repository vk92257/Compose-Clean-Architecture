package com.ui.presentation.screen.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ui.domain.data.Post
import com.ui.presentation.screen.home.SearchBar


@Preview
@Composable
fun SavedArticlesScreen() {
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
                )
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
                    horizontal = 30.dp
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
                    30.dp
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
                items(5) {
                    SavedListItem(
                        Post = Post(
                            body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                            id = 1,
                            userId = 2,
                            isSaved = true,
                            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ",
                        ),
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
                            modifier = Modifier.align(
                                alignment = Alignment.CenterHorizontally
                            ).size(
                                60.dp
                            )
                        )
                    }


                }
            }
        }


    }
}