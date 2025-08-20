package com.rgk.qhatu.shared

expect object SharedImageStorage {
    suspend fun saveImageFromTemp(tempPath: String, suffix: String? = null): String
    suspend fun saveSharedImage(image: SharedImage): String
    suspend fun deleteImage(path: String): Boolean
    suspend fun clearImageCache(): Boolean
}