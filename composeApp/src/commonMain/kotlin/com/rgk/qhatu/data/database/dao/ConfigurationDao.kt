package com.rgk.qhatu.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rgk.qhatu.data.database.entity.ConfigurationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConfigurationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ConfigurationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<ConfigurationEntity>)

    @Query("SELECT * FROM ConfigurationEntity")
    fun fetchAll(): Flow<List<ConfigurationEntity>>

    @Query("SELECT COUNT(*) as count FROM ConfigurationEntity")
    suspend fun count(): Int

}