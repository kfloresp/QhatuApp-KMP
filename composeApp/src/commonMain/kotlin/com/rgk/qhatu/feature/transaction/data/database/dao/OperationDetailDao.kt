package com.rgk.qhatu.feature.transaction.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.transaction.data.database.entity.OperationDetailEntity

@Dao
interface OperationDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detail: OperationDetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(details: List<OperationDetailEntity>)

    @Update
    suspend fun updateDetail(detail: OperationDetailEntity)

    @Delete
    suspend fun deleteDetail(detail: OperationDetailEntity)

    @Query("SELECT * FROM operation_detail WHERE operationId = :operationId")
    suspend fun getDetailsByOperation(operationId: String): List<OperationDetailEntity>
}
