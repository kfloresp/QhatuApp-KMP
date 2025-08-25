package com.rgk.qhatu.common.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


object TimeUtils {
    fun getCurrentTimestamp(): Long {
        return Clock.System.now().toEpochMilliseconds()
    }
    fun formatTimestampToDate(timestamp: Long): String {
        return try {
            if (timestamp == 0L) {
                "-"
            } else {
                val instant = Instant.fromEpochMilliseconds(timestamp)
                val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
                val day = dateTime.date.dayOfMonth.toString().padStart(2, '0')
                val month = (dateTime.date.monthNumber).toString().padStart(2, '0')
                val year = dateTime.date.year
                val hour = dateTime.time.hour.toString().padStart(2, '0')
                val minute = dateTime.time.minute.toString().padStart(2, '0')
                val second = dateTime.time.second.toString().padStart(2, '0')
                "$day/$month/$year $hour:$minute:$second"
            }
        } catch (e: Exception) {
            "-"
        }
    }
}