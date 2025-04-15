package com.rgk.qhatu.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSURL
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

fun getDatabaseBuilder():RoomDatabase.Builder<AppDataBase>{
    val dbFile = "${fileDirectory()}/$DATABASE_NAME"
    return Room.databaseBuilder<AppDataBase>(
        name = dbFile
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
}
@OptIn(ExperimentalForeignApi::class)
private fun fileDirectory(): String {
    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory).path!!
}
