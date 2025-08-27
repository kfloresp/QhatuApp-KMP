package com.rgk.qhatu.common.util

fun generateSaleCode(lastCode: String?, format: String = "NV"): String {
    val now = getCurrentTimestamp()
    val datePart = formatTimestamp(now, "yyMMdd")
    if (lastCode == null) {
        return "$format-$datePart-0001"
    }

    val parts = lastCode.split("-")
    if (parts.size != 3) {
        return "$format-$datePart-0001"
    }

    val lastDatePart = parts[1]
    val lastNumber = parts[2].toIntOrNull() ?: 0

    return if (lastDatePart == datePart) {
        "$format-$datePart-${(lastNumber + 1).toString().padStart(4, '0')}"
    } else {
        "$format-$datePart-0001"
    }
}