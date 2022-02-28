package com.ui.util

import android.content.Context

/**
 * @Author: Vivek
 * @Date: 26/02/22
 */
sealed class UiText {
    data class StringResources(val id: Int) : UiText()
    data class DynamicResources(val text: String) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is StringResources -> context.getString(id)
            is DynamicResources -> text
        }
    }
}
