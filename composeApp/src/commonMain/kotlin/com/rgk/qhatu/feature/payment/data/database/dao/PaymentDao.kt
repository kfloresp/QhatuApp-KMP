package com.rgk.qhatu.feature.payment.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.customer.data.database.entity.CustomerEntity
import com.rgk.qhatu.feature.payment.data.database.entity.PaymentEntity

@Dao
interface PaymentDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: PaymentEntity)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun update(entity: PaymentEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: List<PaymentEntity>)

    @Query("SELECT * FROM payments")
    suspend fun fetchAll(): List<PaymentEntity>

    @Query("SELECT COUNT(*) as count, MAX(lastUpdated) as lastUpdated FROM payments")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM payments WHERE isSynced = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM payments WHERE isSynced != 1")
    suspend fun deleteUnsynced()

    @Query("SELECT * FROM payments WHERE id = :id and isDeleted = false")
    suspend fun fetchById(id: String): List<PaymentEntity>
}