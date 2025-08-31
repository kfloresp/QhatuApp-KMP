package com.rgk.qhatu.feature.operation.data.repository

import com.rgk.qhatu.feature.operation.data.database.dao.OperationDetailDao
import com.rgk.qhatu.feature.operation.domain.mapper.toDomain
import com.rgk.qhatu.feature.operation.domain.mapper.toEntity
import com.rgk.qhatu.feature.operation.domain.model.OperationDetail
import com.rgk.qhatu.feature.operation.domain.repository.OperationDetailRepository

class OperationDetailRepositoryImpl(private val operationDetailDao: OperationDetailDao) :
    OperationDetailRepository {
    override suspend fun insertDetail(detail: OperationDetail) {
        operationDetailDao.insertDetail(detail.toEntity())
    }

    override suspend fun insertDetails(details: List<OperationDetail>) {
        operationDetailDao.insertDetails(details.map { it.toEntity() })
    }

    override suspend fun updateDetail(detail: OperationDetail) {
        operationDetailDao.updateDetail(detail.toEntity())
    }

    override suspend fun deleteDetail(detail: OperationDetail) {
        operationDetailDao.deleteDetail(detail.toEntity())
    }

    override suspend fun getDetailsByOperation(operationId: String): List<OperationDetail> {
        return operationDetailDao.getDetailsByOperation(operationId).map { it.toDomain() }
    }
}