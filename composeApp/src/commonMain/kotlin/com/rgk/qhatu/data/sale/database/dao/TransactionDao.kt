package com.rgk.qhatu.data.sale.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.data.sale.database.entity.TransactionEntity
import com.rgk.qhatu.domain.common.SyncStats

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: TransactionEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: TransactionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: List<TransactionEntity>)

    @Query("SELECT * FROM transactions")
    suspend fun fetchAll(): List<TransactionEntity>

    @Query("SELECT COUNT(*) as count, MAX(fecha_sincronizado) as lastUpdated FROM transactions")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM transactions WHERE flag_sincronizado = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM transactions WHERE flag_sincronizado != 1")
    suspend fun deleteUnsynced()
}
