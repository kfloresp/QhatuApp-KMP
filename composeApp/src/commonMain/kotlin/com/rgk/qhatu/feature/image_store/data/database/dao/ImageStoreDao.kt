package com.rgk.qhatu.feature.image_store.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.image_store.data.database.entity.ImageStoreEntity

@Dao
interface ImageStoreDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun saveAll(entity: List<ImageStoreEntity>)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun updateAll(entity: List<ImageStoreEntity>)

    @Delete
    suspend fun deleteAll(entity: List<ImageStoreEntity>)

    @Query(
        "SELECT * FROM image_store where entityId = :entityId and tableStore = :tableStore"
    )
    suspend fun getImagesById(entityId: String, tableStore: String): List<ImageStoreEntity>
}