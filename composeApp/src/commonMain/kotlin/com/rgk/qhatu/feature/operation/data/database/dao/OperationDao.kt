package com.rgk.qhatu.feature.operation.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.rgk.qhatu.feature.operation.data.database.entity.OperationEntity
import com.rgk.qhatu.feature.operation.data.database.entity.OperationWithDetailRelation

@Dao
interface OperationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: OperationEntity)

    @Update
    suspend fun updateTransaction(transaction: OperationEntity)

    @Delete
    suspend fun deleteTransaction(transaction: OperationEntity)

    @Transaction
    @Query("SELECT * FROM operation WHERE operationId = :operationId")
    suspend fun getOperationWithDetails(operationId: String): OperationWithDetailRelation?

    @Transaction
    @Query("SELECT * FROM operation")
    suspend fun getAllOperationsWithDetails(): List<OperationWithDetailRelation>
}