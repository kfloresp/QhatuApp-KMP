package com.rgk.qhatu.common.util

fun generateUUID(): String {
    val allowedChars = ('a'..'f') + ('0'..'9')
    return (1..32)
        .map { allowedChars.random() }
        .chunked(4)
        .joinToString("-") { it.joinToString("") }
}
fun generateFilename(extension: String = "png"): String {
    val timestamp = getCurrentTimestamp()
    return "IMG_${timestamp}.$extension"
}
