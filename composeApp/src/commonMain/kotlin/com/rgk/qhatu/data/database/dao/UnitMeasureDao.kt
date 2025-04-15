package com.rgk.qhatu.data.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.data.database.entity.TransactionDetailEntity
import com.rgk.qhatu.data.database.entity.UnitMeasureEntity
import com.rgk.qhatu.domain.common.SyncStats
import kotlinx.coroutines.flow.Flow

@Dao
interface UnitMeasureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: UnitMeasureEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: UnitMeasureEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: List<UnitMeasureEntity>)

    @Query("SELECT * FROM unit_measures")
    suspend fun fetchAll(): List<UnitMeasureEntity>

    @Query("SELECT COUNT(*) as count, MAX(fecha_sincronizado) as lastUpdated FROM unit_measures")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM unit_measures WHERE flag_sincronizado = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM unit_measures WHERE flag_sincronizado != 1")
    suspend fun deleteUnsynced()

}
