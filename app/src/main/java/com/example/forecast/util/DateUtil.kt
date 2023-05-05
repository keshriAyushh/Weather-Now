package com.example.forecast.util

import java.time.LocalDateTime

class DateUtil {
    private val date = LocalDateTime.now()
    fun getDate() = FormatText().formatString("${date.dayOfWeek}, ${date.dayOfMonth} ${date.month}, ${date.year}")
}