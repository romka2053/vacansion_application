package com.roman.core

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

interface DateToDayAndMonth {
    val publishedDate: String
    fun getDayMountFromDate(locale: Locale): String {
        val formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd", locale)
        val date = LocalDate.parse(publishedDate, formatDateTime)

        val calendar = Calendar.getInstance()
        calendar.set(date.dayOfMonth, date.monthValue, date.year)
        val day = date.dayOfMonth.toString()
        val month = calendar.getDisplayName(
            Calendar.MONTH,
            Calendar.LONG_FORMAT, Locale(locale.language)
        )

        val dayAndMonth = if (locale.language == Locale("ru").language) {
            "$day $month"
        } else {
            "$month $day"
        }

        return dayAndMonth

    }
}