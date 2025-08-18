package com.rgk.qhatu.shared

import androidx.compose.ui.graphics.ImageBitmap

expect object SharedImageStorage {
    suspend fun saveImage(image: ImageBitmap, filename: String): String
    suspend fun loadImage(path: String): ImageBitmap?
    suspend fun deleteImage(path: String): Boolean
}