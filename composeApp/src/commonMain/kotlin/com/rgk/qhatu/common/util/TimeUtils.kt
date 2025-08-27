package com.rgk.qhatu.common.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getCurrentTimestamp(): Long {
    return Clock.System.now().toEpochMilliseconds()
}
fun formatTimestamp(timestamp: Long, pattern: String = "dd/MM/yyyy HH:mm:ss"): String? {
    val instant = runCatching { Instant.fromEpochMilliseconds(timestamp) }.getOrNull() ?: return null
    val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

    val tokens = mapOf(
        "dd" to dateTime.date.dayOfMonth.toString().padStart(2, '0'),
        "MM" to dateTime.date.monthNumber.toString().padStart(2, '0'),
        "yyyy" to dateTime.date.year.toString(),
        "HH" to dateTime.time.hour.toString().padStart(2, '0'),
        "mm" to dateTime.time.minute.toString().padStart(2, '0'),
        "ss" to dateTime.time.second.toString().padStart(2, '0')
    )

    return tokens.entries.fold(pattern) { acc, (key, value) ->
        acc.replace(key, value)
    }
}