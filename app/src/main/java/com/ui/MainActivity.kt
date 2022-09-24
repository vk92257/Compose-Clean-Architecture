package com.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ui.common.Constants
import com.ui.data.data.dto.newBreeze.Article
import com.ui.navigation.Route
import com.ui.navigation.navigate
import com.ui.presentation.screen.detail.ArticleDetailScreen
import com.ui.presentation.screen.home.Home
import com.ui.presentation.screen.saved.SavedArticlesScreen
import com.ui.presentation.theme.UITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UITheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    Surface(color = MaterialTheme.colors.background) {
                        NavHost(navController = navController, startDestination = Route.HOME) {
                            composable(Route.HOME) {
                                Home(
                                    navigate = {
                                        navController.currentBackStackEntry?.savedStateHandle?.set(
                                            key = Constants.ARTICLE,
                                            value = it
                                        )
                                        navController.navigate(Route.DETAILS)
                                    },
                                    navigateScreen = navController::navigate
                                )
                            }
                            composable(Route.DETAILS) {
                                navController.previousBackStackEntry?.savedStateHandle?.get<Article>(
                                    Constants.ARTICLE
                                )?.let { it1 ->
                                    ArticleDetailScreen(
                                        article = it1
                                    )
                                }
                            }

                            composable(Route.SAVED) {
                                SavedArticlesScreen(
                                    navigate = {
                                        navController.currentBackStackEntry?.savedStateHandle?.set(
                                            key = Constants.ARTICLE,
                                            value = it
                                        )
                                        navController.navigate(Route.DETAILS)
                                    },
                                    navigateUp = { navController.navigateUp() }
                                )
                            }

                        }


                    }
                }
            }
        }
    }
}
