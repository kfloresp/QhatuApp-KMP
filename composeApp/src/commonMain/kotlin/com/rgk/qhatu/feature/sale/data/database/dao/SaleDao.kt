package com.rgk.qhatu.feature.sale.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.rgk.qhatu.feature.sale.data.database.entity.SaleEntity
import com.rgk.qhatu.feature.sale.data.database.entity.SaleWithOperationRelation

@Dao
interface SaleDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertSale(sale: SaleEntity)

    @Transaction
    @Query("SELECT * FROM sale WHERE operationId = :id")
    suspend fun getSaleWithOperation(id: String): SaleWithOperationRelation?

    @Transaction
    @Query("SELECT * FROM sale")
    suspend fun getAllSalesWithOperations(): List<SaleWithOperationRelation>

}