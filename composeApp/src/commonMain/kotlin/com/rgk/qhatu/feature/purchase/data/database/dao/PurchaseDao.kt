package com.rgk.qhatu.feature.purchase.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.rgk.qhatu.feature.purchase.data.database.entity.PurchaseEntity
import com.rgk.qhatu.feature.transaction.data.database.entity.PurchaseWithOperation

@Dao
interface PurchaseDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertPurchase(purchase: PurchaseEntity)

    @Transaction
    @Query("SELECT * FROM purchase WHERE operationId = :id")
    suspend fun getPurchaseWithOperation(id: String): PurchaseEntity?

    @Transaction
    @Query("SELECT * FROM purchase")
    suspend fun getAllPurchasesWithOperations(): List<PurchaseWithOperation>
}