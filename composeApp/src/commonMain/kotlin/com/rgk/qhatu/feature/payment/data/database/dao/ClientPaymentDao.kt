package com.rgk.qhatu.feature.payment.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.payment.data.database.entity.ClientPaymentEntity

@Dao
interface ClientPaymentDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: ClientPaymentEntity)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun update(entity: ClientPaymentEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: List<ClientPaymentEntity>)

    @Query("SELECT * FROM client_payments")
    suspend fun fetchAll(): List<ClientPaymentEntity>

    @Query("SELECT COUNT(*) as count, MAX(lastUpdated) as lastUpdated FROM client_payments")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM client_payments WHERE isSynced = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM client_payments WHERE isSynced != 1")
    suspend fun deleteUnsynced()
}