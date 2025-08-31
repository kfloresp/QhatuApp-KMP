package com.rgk.qhatu.feature.sale.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.sale.domain.model.SaleWithOperation
import com.rgk.qhatu.feature.sale.domain.repository.SaleRepository

class GetSalesUseCase(private val saleRepository: SaleRepository) {
    suspend operator fun invoke(): SyncResult<List<SaleWithOperation>> = safeCall {
        saleRepository.getAllSalesWithOperations()
    }
}