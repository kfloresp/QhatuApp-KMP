package com.rgk.qhatu.feature.operation.domain.repository

import com.rgk.qhatu.feature.operation.domain.model.Operation
import com.rgk.qhatu.feature.operation.domain.model.OperationWithDetail

interface OperationRepository {

    suspend fun insertOperation(operation: Operation)
    suspend fun updateOperation(operation: Operation)
    suspend fun deleteOperation(operation: Operation)
    suspend fun getOperationById(operationId: String): OperationWithDetail?
    suspend fun getAllOperations(): List<OperationWithDetail>

}