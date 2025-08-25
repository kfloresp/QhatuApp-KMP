package com.rgk.qhatu.feature.operation.domain.repository

import com.rgk.qhatu.feature.operation.domain.model.OperationDetail

interface OperationDetailRepository {

    suspend fun insertDetail(detail: OperationDetail)
    suspend fun insertDetails(details: List<OperationDetail>)
    suspend fun updateDetail(detail: OperationDetail)
    suspend fun deleteDetail(detail: OperationDetail)
    suspend fun getDetailsByOperation(operationId: String): List<OperationDetail>

}