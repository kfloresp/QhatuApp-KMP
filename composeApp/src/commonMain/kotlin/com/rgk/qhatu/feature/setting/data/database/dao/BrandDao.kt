package com.rgk.qhatu.feature.setting.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.setting.data.database.entity.BrandEntity

@Dao
interface BrandDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: BrandEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: List<BrandEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: BrandEntity)

    @Query("SELECT * FROM product_brand where isDeleted = false")
    suspend fun fetchAll(): List<BrandEntity>
}
