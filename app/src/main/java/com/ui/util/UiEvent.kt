package com.ui.util

import com.ui.data.data.dto.newBreeze.Article

/**
 * @Author: Vivek
 * @Date: 26/02/22
 */
sealed class UiEvent{
    data class Navigate(val route:String): UiEvent()
    data class ReadArticleClick(val article: Article): UiEvent()
    data class ShowSnackBar(val message: String): UiEvent()
    object NavigateUp : UiEvent()
}
