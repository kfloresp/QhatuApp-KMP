package com.rgk.qhatu.shared

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File
import java.io.FileOutputStream

actual object SharedImageStorage {
    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    actual suspend fun saveImage(image: ImageBitmap, filename: String): String {
        val file = File(appContext.filesDir, filename)
        val bitmap: Bitmap = image.asAndroidBitmap()
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)
        }
        return file.absolutePath
    }

    actual suspend fun loadImage(path: String): ImageBitmap? {
        val file = File(path)
        if (!file.exists()) return null
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        return bitmap?.asImageBitmap()
    }

    actual suspend fun deleteImage(path: String): Boolean {
        val file = File(path)
        return file.delete()
    }
}