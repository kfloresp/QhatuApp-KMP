package com.rgk.qhatu.common.components.datepicker
import androidx.compose.material3.*
import androidx.compose.runtime.*
import kotlinx.datetime.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDatePicker(
    showPicker: Boolean,
    initialDate: LocalDate? = null,
    onDateSelected: (LocalDate?) -> Unit,
    onDismiss: () -> Unit
) {
    val today = Clock.System.today()
    val oneWeekAgo = today.minusDays(7)
    if (showPicker) {
        val datePickerState = rememberDatePickerState(
            yearRange = oneWeekAgo.year..today.year,
            initialSelectedDateMillis = initialDate?.toEpochMilliseconds(),
            selectableDates = object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return Instant.fromEpochMilliseconds(utcTimeMillis)
                        .toLocalDateTime(TimeZone.UTC)
                        .date
                        .isWithinLastWeek()
                }
            }
        )

        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val date = Instant.fromEpochMilliseconds(millis)
                                .toLocalDateTime(TimeZone.UTC).date
                            onDateSelected(date)
                        } ?: onDateSelected(null)
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

fun LocalDate.toEpochMilliseconds(): Long {
    return LocalDateTime(year, monthNumber, dayOfMonth, 0, 0, 0)
        .toInstant(TimeZone.UTC)
        .toEpochMilliseconds()
}

fun Clock.System.today(): LocalDate {
    return this.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
}

fun LocalDate.minusDays(days: Int): LocalDate {
    return this.minus(days, DateTimeUnit.DAY)
}

private fun LocalDate.formatWithPattern(): String {
    val day = dayOfMonth.toString().padStart(2, '0')
    val month = monthNumber.toString().padStart(2, '0')
    val yearStr = year.toString()
    return "$day/$month/$yearStr"
}

fun LocalDate.isWithinLastWeek(): Boolean {
    val today = Clock.System.today()
    val oneWeekAgo = today.minusDays(7)
    return this in oneWeekAgo..today
}

fun LocalDate?.toFormat(): String {
    return try {
        val date = this ?: Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
        date.formatWithPattern()
    } catch (e: Exception) {
        val today = Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
        today.formatWithPattern()
    }
}