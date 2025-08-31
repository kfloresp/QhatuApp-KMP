package com.rgk.qhatu.feature.operation.data.repository

import com.rgk.qhatu.feature.operation.data.database.dao.OperationDao
import com.rgk.qhatu.feature.operation.domain.mapper.toDomain
import com.rgk.qhatu.feature.operation.domain.mapper.toEntity
import com.rgk.qhatu.feature.operation.domain.model.Operation
import com.rgk.qhatu.feature.operation.domain.model.OperationWithDetail
import com.rgk.qhatu.feature.operation.domain.repository.OperationRepository

class OperationRepositoryImpl(private val operationDao: OperationDao) : OperationRepository {
    override suspend fun insertOperation(operation: Operation) {
        operationDao.insertTransaction(operation.toEntity())
    }

    override suspend fun updateOperation(operation: Operation) {
        operationDao.updateTransaction(operation.toEntity())
    }

    override suspend fun deleteOperation(operation: Operation) {
        operationDao.deleteTransaction(operation.toEntity())
    }

    override suspend fun getOperationById(operationId: String): OperationWithDetail? {
        return operationDao.getOperationWithDetails(operationId)?.toDomain()
    }

    override suspend fun getAllOperations(): List<OperationWithDetail> {
        return operationDao.getAllOperationsWithDetails().map { it.toDomain() }
    }
}