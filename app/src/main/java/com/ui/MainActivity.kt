package com.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ui.navigation.Route
import com.ui.navigation.navigate
import com.ui.presentation.screen.Home
import com.ui.presentation.theme.UITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
                                Home(navigate = navController::navigate)
                            }
                            composable(Route.DETAILS) {

                            }

                        }


                    }
                }
            }
        }
    }
}
