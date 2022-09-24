package com.ui.common

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
internal fun String.toTime(): String {
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        formatter.format(parser.parse(this))
    } catch (e: Exception) {
        return this
    }
}