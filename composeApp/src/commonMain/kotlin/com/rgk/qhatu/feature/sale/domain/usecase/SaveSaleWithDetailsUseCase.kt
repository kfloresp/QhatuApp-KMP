package com.rgk.qhatu.feature.sale.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.util.generateSaleCode
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.common.util.getCurrentTimestamp
import com.rgk.qhatu.feature.cart.domain.repository.CartRepository
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
        val lastVoucherOperationNo = saleRepository.getLastVoucherOperationNo()
        val subTotalWithIGV: Double = saleWithOperation.details.sumOf { it.totalPrice }
        val totalDiscounts: Double = saleWithOperation.details.sumOf { it.totalDiscount }

        val operation = saleWithOperation.operation.copy(
            operationId = operationId,
            type = OperationType.SALE,
            lastUpdated = lastUpdate,
        )
        val sale = saleWithOperation.sale.copy(
            operationId = operationId,
            voucherOperationNo = generateSaleCode(lastVoucherOperationNo),
            subtotalWithIGV = subTotalWithIGV,
            totalDiscounts = totalDiscounts,
        )
        val details = saleWithOperation.details.map {
            it.copy(operationId = operationId, lastUpdated = lastUpdate)
        }

        operationRepository.insertOperation(operation)
        saleRepository.insertSale(sale)
        operationDetailRepository.insertDetails(details)
        cartRepository.deleteCart()
    }
}