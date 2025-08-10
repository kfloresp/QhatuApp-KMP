package com.rgk.qhatu.feature.payment.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.customer.data.database.entity.CustomerEntity
import com.rgk.qhatu.feature.payment.data.database.entity.PaymentEntity
import com.rgk.qhatu.feature.payment.domain.model.Payment

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

    @Query("""
        SELECT 
            p.id,
            cu.id AS customerId,
            cu.nombre || ' ' || cu.apellidoPaterno || ' ' || cu.apellidoMaterno AS customer,
            p.paymentDate,
            p.amountPaid,
            co.id AS paymentMethodId,
            co.nombre AS paymentMethod,
            p.comments,
            p.numberOperation,
            p.isSynced,
            p.isDeleted,
            p.lastUpdated
        FROM payments p
        INNER JOIN clients cu ON p.clientId = cu.id
        INNER JOIN configurations co ON p.paymentMethodId = co.id
        WHERE p.id = :id and p.isDeleted = false
    """)
    suspend fun fetchById(id: String): List<Payment>

    @Query("""
        SELECT 
            p.id, 
            cu.id AS customerId,
            cu.nombre || ' ' || cu.apellidoPaterno || ' ' || cu.apellidoMaterno AS customer,
            p.paymentDate,
            p.amountPaid,
            co.id AS paymentMethodId,
            co.nombre AS paymentMethod,
            p.comments,
            p.numberOperation,
            p.isSynced,
            p.isDeleted,
            p.lastUpdated
        FROM payments p
        INNER JOIN clients cu ON p.clientId = cu.id
        INNER JOIN configurations co ON p.paymentMethodId = co.id
        WHERE p.isDeleted = false
    """)
    suspend fun getPaymentsWithDetails(): List<Payment>
}