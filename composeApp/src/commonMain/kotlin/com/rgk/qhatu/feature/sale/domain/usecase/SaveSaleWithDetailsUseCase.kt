package com.rgk.qhatu.feature.sale.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.util.TimeUtils.getCurrentTimestamp
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.feature.cart.domain.repository.CartRepository
import com.rgk.qhatu.feature.operation.domain.model.OperationStatus
import com.rgk.qhatu.feature.operation.domain.model.OperationType
import com.rgk.qhatu.feature.operation.domain.repository.OperationDetailRepository
import com.rgk.qhatu.feature.operation.domain.repository.OperationRepository
import com.rgk.qhatu.feature.sale.domain.model.SaleWithOperation
import com.rgk.qhatu.feature.sale.domain.repository.SaleRepository

class SaveSaleWithDetailsUseCase(
    private val saleRepository: SaleRepository,
    private val operationRepository: OperationRepository,
    private val operationDetailRepository: OperationDetailRepository,
    private val cartRepository: CartRepository,
) {
    suspend operator fun invoke(saleWithOperation: SaleWithOperation): SyncResult<Unit> = safeCall {
        val operationId = generateUUID()
        val lastUpdate = getCurrentTimestamp()
        val operation = saleWithOperation.operation.copy(
            operationId = operationId,
            type = OperationType.SALE,
            lastUpdated = lastUpdate,
        )
        val sale = saleWithOperation.sale.copy(
            operationId = operationId,
        )
        val details = saleWithOperation.details.map {
            it.copy(operationId = operationId, lastUpdated = lastUpdate)
        }

        operationRepository.insertOperation(operation)
        saleRepository.insertSale(sale)

        val operationStatus = if (details.isNotEmpty()) {
            operationDetailRepository.insertDetails(details)
            OperationStatus.COMPLETED
        } else {
            OperationStatus.CANCELLED
        }

        operationRepository.updateOperation(
            operation.copy(status = operationStatus)
        )
        cartRepository.deleteCart()
    }
}