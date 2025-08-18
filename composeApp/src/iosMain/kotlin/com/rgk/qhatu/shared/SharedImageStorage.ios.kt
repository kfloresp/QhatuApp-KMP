package com.rgk.qhatu.shared

import androidx.compose.ui.graphics.ImageBitmap

actual object SharedImageStorage {
    actual suspend fun saveImage(
        image: ImageBitmap,
        filename: String,
    ): String {
        TODO("Not yet implemented")
    }

    actual suspend fun loadImage(path: String): ImageBitmap? {
        TODO("Not yet implemented")
    }

    actual suspend fun deleteImage(path: String): Boolean {
        TODO("Not yet implemented")
    }
}