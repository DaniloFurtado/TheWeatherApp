package com.danilo.furtado.theweatherapp.extensions

import android.text.format.DateUtils
import com.danilo.furtado.theweatherapp.R.string
import java.text.DateFormat
import java.text.DateFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date?.getDateFormattedHours(): String {
    if (this == null) return ""
    val formatter: DateFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
    return formatter.format(this)
}

fun Date?.getDateFormattedFullDescription(): String {
    if (this == null) return ""
    val formatter: DateFormat = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())
    return formatter.format(this)
}

fun Date.getStringDateTimeFromPubDate(): String {
    return try {
        val time: Long = this.time

        DateUtils
            .getRelativeTimeSpanString(time).toString()
    } catch (e: ParseException) {
        "Error parse date"
    }
}

fun Date.getDescDayOfDat(): String {
    val calendar = Calendar.getInstance()
    val date = Calendar.getInstance().apply { timeInMillis = this@getDescDayOfDat.time }
    return when (date.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR)) {
        0 -> {
            TODAY
        }
        1 -> {
            TOMORROW
        }
        else -> {
            val symbols = DateFormatSymbols(Locale.getDefault())
            return symbols.weekdays[date.get(Calendar.DAY_OF_WEEK)]
        }
    }
}

const val TODAY = "today"
const val TOMORROW = "tomorrow"