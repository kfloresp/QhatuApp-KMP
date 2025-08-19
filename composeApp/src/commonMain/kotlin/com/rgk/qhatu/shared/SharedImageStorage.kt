package com.rgk.qhatu.shared

import androidx.compose.ui.graphics.ImageBitmap

expect object SharedImageStorage {
    suspend fun saveImageFromTemp(tempPath: String, suffix: String? = null): String
    suspend fun saveTempImage(image: ImageBitmap): String
    suspend fun deleteImage(path: String): Boolean
    suspend fun clearImageCache(): Boolean
}