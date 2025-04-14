package com.rgk.qhatu.data.database

import android.app.Application
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

actual class Factory(
    private val app:Application
){
    actual fun createRoomDatabase(): AppDataBase {
        val dbFile = app.getDatabasePath(DATABASE_NAME)
        return Room
            .databaseBuilder<AppDataBase>(
                context = app,
                name = dbFile.absolutePath,
            ).setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}
