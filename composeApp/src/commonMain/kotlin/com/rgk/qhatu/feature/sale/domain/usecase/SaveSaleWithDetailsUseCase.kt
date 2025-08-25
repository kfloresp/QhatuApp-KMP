package com.rgk.qhatu.feature.sale.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.operation.domain.model.OperationType
import com.rgk.qhatu.feature.operation.domain.repository.OperationDetailRepository
import com.rgk.qhatu.feature.operation.domain.repository.OperationRepository
import com.rgk.qhatu.feature.sale.domain.model.SaleWithOperation
import com.rgk.qhatu.feature.sale.domain.repository.SaleRepository

class SaveSaleWithDetailsUseCase(
    private val saleRepository: SaleRepository,
    private val operationRepository: OperationRepository,
    private val operationDetailRepository: OperationDetailRepository,
) {
    suspend operator fun invoke(saleWithOperation: SaleWithOperation): SyncResult<Unit> = safeCall {
        val operationId = saleWithOperation.operation.operationId
        operationRepository.insertOperation(saleWithOperation.operation.copy(type = OperationType.SALE))
        saleRepository.insertSale(saleWithOperation.sale.copy(operationId = operationId))
        if (saleWithOperation.details.isNotEmpty()) {
            operationDetailRepository.insertDetails(saleWithOperation.details.map {
                it.copy(
                    operationId = operationId
                )
            })
        }
    }
}