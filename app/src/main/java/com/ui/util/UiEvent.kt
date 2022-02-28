package com.ui.navigation

/**
 * @Author: Vivek
 * @Date: 26/02/22
 */
sealed class UiEvent{
    data class Navigate(val route:String): UiEvent()
    data class ShowSnackBar(val message: String): UiEvent()
    object NavigateUp : UiEvent()
}
