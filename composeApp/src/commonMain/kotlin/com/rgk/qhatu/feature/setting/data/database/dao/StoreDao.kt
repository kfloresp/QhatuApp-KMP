package com.rgk.qhatu.feature.setting.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.setting.data.database.entity.StoreEntity

@Dao
interface StoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: StoreEntity)

    @Update
    suspend fun update(entity: StoreEntity)

    @Query("SELECT * FROM store limit 1")
    suspend fun fetchLocal(): StoreEntity?
}