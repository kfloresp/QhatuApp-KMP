package com.rgk.qhatu.feature.setting.data.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.setting.data.database.entity.BrandEntity
import com.rgk.qhatu.common.model.SyncStats

@Dao
interface BrandDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: BrandEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: BrandEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: List<BrandEntity>)

    @Query("SELECT * FROM brands")
    suspend fun fetchAll(): List<BrandEntity>

    @Query("SELECT COUNT(*) as count, MAX(fecha_actualizacion) as lastUpdated FROM brands")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM brands WHERE flag_sincronizado = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM brands WHERE flag_sincronizado != 1")
    suspend fun deleteUnsynced()
}
