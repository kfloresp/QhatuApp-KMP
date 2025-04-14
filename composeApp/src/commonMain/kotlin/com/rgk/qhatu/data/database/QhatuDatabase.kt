package com.rgk.qhatu.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.rgk.qhatu.data.database.dao.ConfigurationDao
import com.rgk.qhatu.data.database.entity.ConfigurationEntity

const val DATABASE_NAME = "qhatu_database.db"

@Database(entities = [ConfigurationEntity::class], version = 1, exportSchema = false)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDataBase : RoomDatabase(){
    abstract fun configurationDao(): ConfigurationDao
}
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDataBase> {
    override fun initialize(): AppDataBase
}
