package com.rgk.qhatu.common.util

fun Double?.orZero(): Double = this ?: 0.0
fun Double.formatAmount(currencySymbol: String = "S/."): String {
    val rounded = kotlin.math.round(this * 100) / 100
    val parts = rounded.toString().split(".")
    val integer = parts[0]
    val decimal = parts.getOrNull(1)?.padEnd(2, '0')?.take(2) ?: "00"
    return "$currencySymbol$integer.$decimal"
}



