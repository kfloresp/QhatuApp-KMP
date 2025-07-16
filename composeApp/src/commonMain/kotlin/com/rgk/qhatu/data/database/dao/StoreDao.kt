package com.rgk.qhatu.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.data.database.entity.StoreEntity
import com.rgk.qhatu.domain.common.SyncStats

@Dao
interface StoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: StoreEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entities: List<StoreEntity>)

    @Update
    suspend fun update(entity: StoreEntity)

    @Query("SELECT * FROM stores")
    suspend fun fetchAll(): List<StoreEntity>

    @Query("SELECT * FROM stores WHERE id = :storeId")
    suspend fun findById(storeId: String): StoreEntity?

    @Query("SELECT COUNT(*) as count, MAX(fechaSincronizado) as lastUpdated FROM stores")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM stores WHERE flagSincronizado = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM stores WHERE flagSincronizado != 1")
    suspend fun deleteUnsynced()

    @Query("DELETE FROM stores")
    suspend fun deleteAll()
}