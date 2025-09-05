package com.rgk.qhatu.shared


actual object SharedImageStorage {

    actual suspend fun deleteImage(path: String): Boolean {
        TODO("Not yet implemented")
    }

    actual suspend fun clearImageCache(): Boolean {
        TODO("Not yet implemented")
    }

    actual suspend fun saveImageFromTemp(tempPath: String, suffix: String?): String {
        TODO("Not yet implemented")
    }

    actual suspend fun saveSharedImage(image: SharedImage): String {
        TODO("Not yet implemented")
    }
}