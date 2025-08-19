package com.rgk.qhatu.shared

import androidx.compose.ui.graphics.ImageBitmap

actual object SharedImageStorage {

    actual suspend fun deleteImage(path: String): Boolean {
        TODO("Not yet implemented")
    }

    actual suspend fun saveTempImage(image: ImageBitmap): String {
        TODO("Not yet implemented")
    }

    actual suspend fun clearImageCache(): Boolean {
        TODO("Not yet implemented")
    }

    actual suspend fun saveImageFromTemp(tempPath: String, suffix: String?): String {
        TODO("Not yet implemented")
    }
}