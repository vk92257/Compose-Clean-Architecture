package com.ui.common

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.text.format.DateFormat
import androidx.annotation.RequiresApi
import java.util.*

object Util {

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    fun extractDate(date: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        return formatter.format(parser.parse(date))

    }


    fun getFormattedDate( smsTimeInMilis: Long): String {
        val smsTime: Calendar = Calendar.getInstance()
        smsTime.setTimeInMillis(smsTimeInMilis)
        val now: Calendar = Calendar.getInstance()
        val timeFormatString = "h:mm aa"
        val dateTimeFormatString = "EEEE, MMMM d, h:mm aa"
        val HOURS = (60 * 60 * 60).toLong()
        return if (now.get(Calendar.DATE) === smsTime.get(Calendar.DATE)) {
            "Today " + DateFormat.format(timeFormatString, smsTime)
        } else if (now.get(Calendar.DATE) - smsTime.get(Calendar.DATE) === 1) {
            "Yesterday " + DateFormat.format(timeFormatString, smsTime)
        } else if (now.get(Calendar.YEAR) === smsTime.get(Calendar.YEAR)) {
            DateFormat.format(dateTimeFormatString, smsTime).toString()
        } else {
            DateFormat.format("MMMM dd yyyy, h:mm aa", smsTime).toString()
        }
    }

}