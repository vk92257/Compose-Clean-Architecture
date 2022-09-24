package com.ui.navigation

import androidx.navigation.NavController
import com.ui.util.UiEvent

/**
 * @Author: Vivek
 * @Date: 26/02/22
 */

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}