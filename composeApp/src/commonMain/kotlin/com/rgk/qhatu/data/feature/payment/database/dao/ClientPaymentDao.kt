package com.rgk.qhatu.data.feature.payment.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.data.feature.payment.database.entity.ClientPaymentEntity
import com.rgk.qhatu.domain.common.SyncStats

@Dao
interface ClientPaymentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: ClientPaymentEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: ClientPaymentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: List<ClientPaymentEntity>)

    @Query("SELECT * FROM client_payments")
    suspend fun fetchAll(): List<ClientPaymentEntity>

    @Query("SELECT COUNT(*) as count, MAX(fecha_sincronizado) as lastUpdated FROM client_payments")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM client_payments WHERE flag_sincronizado = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM client_payments WHERE flag_sincronizado != 1")
    suspend fun deleteUnsynced()
}
