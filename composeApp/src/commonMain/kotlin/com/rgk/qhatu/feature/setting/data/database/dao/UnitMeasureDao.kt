package com.rgk.qhatu.feature.setting.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.setting.data.database.entity.UnitMeasureEntity
import com.rgk.qhatu.common.model.SyncStats

@Dao
interface UnitMeasureDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: UnitMeasureEntity)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun update(entity: UnitMeasureEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: List<UnitMeasureEntity>)

    @Query("SELECT * FROM product_unit_of_measure where isDeleted = false")
    suspend fun fetchAll(): List<UnitMeasureEntity>

    @Query("SELECT COUNT(*) as count, MAX(lastUpdated) as lastUpdated FROM product_unit_of_measure")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM product_unit_of_measure WHERE isSynced = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM product_unit_of_measure WHERE isSynced != 1")
    suspend fun deleteUnsynced()

}