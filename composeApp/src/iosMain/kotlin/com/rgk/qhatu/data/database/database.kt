package com.rgk.qhatu.data.database

import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

actual class Factory {
    actual fun createRoomDatabase(): AppDataBase {
        val dbFilePath = NSHomeDirectory() + "/$DATABASE_NAME"
        return Room.databaseBuilder<AppDataBase>(
            name = dbFilePath
        ).setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}
