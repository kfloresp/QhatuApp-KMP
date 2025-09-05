package com.rgk.qhatu.feature.setting.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.setting.data.database.entity.UnitMeasureEntity

@Dao
interface UnitMeasureDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: UnitMeasureEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: List<UnitMeasureEntity>)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun update(entity: UnitMeasureEntity)

    @Query("SELECT * FROM product_unit_of_measure where isDeleted = false")
    suspend fun fetchAll(): List<UnitMeasureEntity>
}