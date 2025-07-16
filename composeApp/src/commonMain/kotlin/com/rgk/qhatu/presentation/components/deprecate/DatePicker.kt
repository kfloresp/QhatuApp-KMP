package com.rgk.qhatu.presentation.components.deprecate

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.*

// 📅 Step 1: Define the Date Picker Demo Composable 📅
@OptIn(ExperimentalMaterial3Api::class) // 🚨 Use Experimental Material3 API for DatePicker
@Composable
fun DatePickerDemo() {
    // 🧠 Step 2: Initialize State Variables
    var showDatePicker by remember { mutableStateOf(false) } // 🎯 Toggle dialog visibility
    var selectedDate by remember { mutableStateOf("") } // 📆 Store selected date
    var showError by remember { mutableStateOf(false) } // ⚠️ Show error for invalid dates

    // 🖼️ Step 3: Create the Main UI Layout
    Column(
        Modifier
            .padding(16.dp) // 🛑 Add padding
            .fillMaxWidth(), // 📏 Full width
        verticalArrangement = Arrangement.spacedBy(12.dp) // 📐 Space items by 12dp
    ) {
        // 🃏 Step 4: Add Date Picker Card
        DatePickerCard(
            title = "Future Only", // 📌 Card title
            description = "Select dates from today onward.", // ℹ️ Card description
            date = selectedDate, // 📅 Selected date
            buttonText = "Select Date", // 🖱️ Button text
            onClick = { showDatePicker = true } // 🔄 Show dialog on click
        )
    }

    // 🗳️ Step 5: Show Date Picker Dialog
    if (showDatePicker) {
        // 📆 Step 6: Configure Date Picker State
        val datePickerState = rememberDatePickerState(
            selectableDates = object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    val date = Instant.fromEpochMilliseconds(utcTimeMillis)
                        .toLocalDateTime(TimeZone.currentSystemDefault()).date
                    return validateDate(date, RestrictionType.NoPastDates) // ✅ Restrict past dates
                }
            }
        )
        // 🖼️ Step 7: Create Date Picker Dialog
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false }, // 🚪 Close on dismiss
            confirmButton = {
                // ✅ Step 8: Handle Confirm Button
                TextButton(
                    onClick = {
                        val selectedMillis = datePickerState.selectedDateMillis
                        if (selectedMillis != null) {
                            val date = Instant.fromEpochMilliseconds(selectedMillis)
                                .toLocalDateTime(TimeZone.currentSystemDefault()).date
                            if (!validateDate(date, RestrictionType.NoPastDates)) {
                                showError = true // 🛑 Show error for past dates
                            } else {
                                selectedDate = date.toString() // 💾 Save valid date
                                showDatePicker = false // 🚪 Close dialog
                                showError = false // 🟢 Clear error
                            }
                        }
                    }
                ) {
                    Text("OK", color = Color(0xFF3F51B5)) // 🎨 Blue OK button
                }
            },
            dismissButton = {
                // ❌ Step 9: Handle Cancel Button
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel", color = Color(0xFF3F51B5)) // 🎨 Blue Cancel button
                }
            },
            colors = DatePickerDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surface, // 🖼️ Dialog background
                titleContentColor = Color(0xFF3F51B5) // 🎨 Blue title
            )
        ) {
            // 📋 Step 10: Dialog Content
            Column {
                // 📅 Step 11: Add Date Picker
                DatePicker(
                    state = datePickerState, // 🧠 Link to state
                    headline = {
                        Text(
                            text = "Future Only", // 📌 Dialog title
                            style = MaterialTheme.typography.titleLarge, // 📏 Large text
                            color = Color(0xFF3F51B5), // 🎨 Blue text
                            modifier = Modifier.padding(16.dp) // 🛑 Padding
                        )
                    },
                    modifier = Modifier
                )
                // ⚠️ Step 12: Show Error Message
                if (showError) {
                    Text(
                        text = "Selected date is not allowed. Please choose a valid date.", // 🛑 Error text
                        color = Color.Red, // 🎨 Red color
                        style = MaterialTheme.typography.bodySmall, // 📏 Small text
                        modifier = Modifier.padding(16.dp) // 🛑 Padding
                    )
                }
            }
        }
    }
}

// 🃏 Step 13: Reusable Date Picker Card Component 🃏
@Composable
private fun DatePickerCard(
    title: String,
    description: String,
    date: String,
    buttonText: String,
    onClick: () -> Unit
) {
    // 🖼️ Step 14: Create Card Layout
    Card(
        modifier = Modifier
            .fillMaxWidth() // 📏 Full width
            .clip(RoundedCornerShape(16.dp)) // 🔲 Rounded corners
            .border(1.dp, Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(16.dp)), // 🖌️ Light gray border
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface) // 🖼️ Surface background
    ) {
        Column(Modifier.padding(16.dp)) { // 🛑 Padding inside card
            // 📌 Step 15: Add Title
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), // 📏 Bold large text
                color = MaterialTheme.colorScheme.onSurface // 🎨 Surface text color
            )
            Spacer(Modifier.height(8.dp)) // 📐 Space
            // ℹ️ Step 16: Add Description
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium, // 📏 Medium text
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f) // 🎨 Slightly transparent
            )
            Spacer(Modifier.height(12.dp)) // 📐 Space
            // 🖱️ Step 17: Add Button
            Button(
                onClick = onClick, // 🔄 Trigger onClick
                modifier = Modifier.align(Alignment.End), // 📍 Align right
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary, // 🎨 Primary color
                    contentColor = MaterialTheme.colorScheme.onPrimary // 🎨 On-primary text
                ),
                shape = RoundedCornerShape(12.dp) // 🔲 Rounded button
            ) {
                Text(buttonText, fontSize = 14.sp) // 📏 Button text
            }
            // 📅 Step 18: Show Selected Date
            if (date.isNotEmpty()) {
                Spacer(Modifier.height(8.dp)) // 📐 Space
                Text(
                    text = "Selected: $date", // 📅 Display date
                    style = MaterialTheme.typography.bodyLarge, // 📏 Large text
                    color = MaterialTheme.colorScheme.onSurface // 🎨 Surface text color
                )
            }
        }
    }
}

// 🔧 Step 19: Date Validation Logic 🔧
private fun validateDate(date: LocalDate, restrictionType: RestrictionType): Boolean {
    // ⏰ Step 20: Get Current Date
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    // ✅ Step 21: Validate Based on Restriction
    return when (restrictionType) {
        RestrictionType.NoPastDates -> date >= today // 🛑 No past dates
        else -> true // ✅ Allow all for None
    }
}

// 🎨 Step 22: Enum for Restriction Types 🎨
enum class RestrictionType {
    None, NoPastDates
}