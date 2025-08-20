package com.rgk.qhatu.shared

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import com.rgk.qhatu.common.util.generateFilename
import java.io.File
import java.io.FileOutputStream

actual object SharedImageStorage {
    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    actual suspend fun saveImageFromTemp(tempPath: String, suffix: String?): String {
        val tempFile = File(tempPath)
        if (!tempFile.exists()) {
            throw IllegalArgumentException("Temp file not found at: $tempPath")
        }

        val originalName = tempFile.nameWithoutExtension
        val extension = tempFile.extension

        val finalName = if (!suffix.isNullOrBlank()) {
            "$originalName-$suffix.$extension"
        } else {
            "$originalName.$extension"
        }

        val finalFile = File(appContext.filesDir, finalName)

        tempFile.copyTo(finalFile, overwrite = true)

        deleteImage(tempFile.path)

        return finalFile.absolutePath
    }

    actual suspend fun deleteImage(path: String): Boolean {
        if (path.isBlank()) return false

        val file = File(path)
        if (!file.exists()) return false
        if (!file.canWrite()) return false

        return file.delete()
    }

    actual suspend fun clearImageCache(): Boolean {
        return try {
            val cacheDir = appContext.cacheDir
            if (cacheDir != null && cacheDir.exists()) {
                deleteRecursively(cacheDir)
            } else {
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun deleteRecursively(file: File): Boolean {
        return try {
            if (file.isDirectory) {
                file.listFiles()?.forEach { child ->
                    deleteRecursively(child)
                }
            }
            file.delete()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    actual suspend fun saveSharedImage(image: SharedImage): String {
        val file = File(appContext.cacheDir, generateFilename())
        image.getBitmap()?.let { bitmap ->
            FileOutputStream(file).use { out ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, out)
            }
        }
        return file.absolutePath
    }

}