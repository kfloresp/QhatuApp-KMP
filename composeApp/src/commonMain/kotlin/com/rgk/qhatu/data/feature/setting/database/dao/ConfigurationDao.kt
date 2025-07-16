package com.rgk.qhatu.data.feature.setting.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.data.feature.setting.database.entity.ConfigurationEntity
import com.rgk.qhatu.domain.common.SyncStats

@Dao
interface ConfigurationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: ConfigurationEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: ConfigurationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: List<ConfigurationEntity>)

    @Query("SELECT * FROM configurations")
    suspend fun fetchAll(): List<ConfigurationEntity>

    @Query("SELECT COUNT(*) as count, MAX(fecha_sincronizado) as lastUpdated FROM configurations")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM configurations WHERE flag_sincronizado = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM configurations WHERE flag_sincronizado != 1")
    suspend fun deleteUnsynced()

}