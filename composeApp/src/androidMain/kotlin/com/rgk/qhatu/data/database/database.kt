package com.rgk.qhatu.data.database
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rgk.qhatu.di.AppDataBase
import com.rgk.qhatu.di.DATABASE_NAME
import kotlinx.coroutines.Dispatchers

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<AppDataBase>{
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<AppDataBase>(
        context = appContext,
        name = dbFile.absolutePath
    ).setQueryCoroutineContext(Dispatchers.IO)
}