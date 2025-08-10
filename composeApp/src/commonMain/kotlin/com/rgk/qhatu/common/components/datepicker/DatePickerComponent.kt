package com.rgk.qhatu.common.components.datepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rgk.qhatu.common.theme.QhatuTheme
import kotlinx.datetime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerComponent(
    showPicker: Boolean,
    initialDate: Long,
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit,
) {
    val today = Clock.System.today()
    val oneWeekAgo = today.minusDays(7)
    if (showPicker) {
        val datePickerState = rememberDatePickerState(
            yearRange = oneWeekAgo.year..today.year,
            initialSelectedDateMillis = initialDate,
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
                            onDateSelected(millis)
                        } ?: onDateSelected(0L)
                        onDismiss()
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

fun Long.toFormattedDate(): String {
    val instant = Instant.fromEpochMilliseconds(this)
    val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

    val months = listOf(
        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
    )

    val day = dateTime.dayOfMonth + 1
    val month = months[dateTime.monthNumber - 1]
    val year = dateTime.year

    return "$day de $month, $year"
}

@Preview
@Composable
fun DatePickerPreview() {
    QhatuTheme {
        Column(modifier = Modifier.background(Color.White)) {
            DatePickerComponent(
                showPicker = true,
                initialDate = Clock.System.today().toEpochMilliseconds(),
                onDateSelected = {

                },
                onDismiss = {

                }
            )
        }
    }
}