package com.rgk.qhatu.feature.payment.data.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.payment.data.database.entity.PaymentTransactionEntity
import com.rgk.qhatu.common.model.SyncStats

@Dao
interface PaymentTransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: PaymentTransactionEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: PaymentTransactionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: List<PaymentTransactionEntity>)

    @Query("SELECT * FROM payment_transactions")
    suspend fun fetchAll(): List<PaymentTransactionEntity>

    @Query("SELECT COUNT(*) as count, MAX(fecha_sincronizado) as lastUpdated FROM payment_transactions")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM payment_transactions WHERE flag_sincronizado = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM payment_transactions WHERE flag_sincronizado != 1")
    suspend fun deleteUnsynced()
}
