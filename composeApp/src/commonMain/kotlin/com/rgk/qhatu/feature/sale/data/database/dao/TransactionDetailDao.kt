package com.rgk.qhatu.feature.sale.data.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.sale.data.database.entity.TransactionDetailEntity
import com.rgk.qhatu.common.model.SyncStats

@Dao
interface TransactionDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: TransactionDetailEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: TransactionDetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: List<TransactionDetailEntity>)

    @Query("SELECT * FROM transaction_details")
    suspend fun fetchAll(): List<TransactionDetailEntity>

    @Query("SELECT COUNT(*) as count, MAX(fecha_sincronizado) as lastUpdated FROM transaction_details")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM transaction_details WHERE flag_sincronizado = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM transaction_details WHERE flag_sincronizado != 1")
    suspend fun deleteUnsynced()

}
