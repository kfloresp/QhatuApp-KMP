package com.rgk.qhatu.feature.sale.domain.repository

import com.rgk.qhatu.feature.sale.domain.model.Sale
import com.rgk.qhatu.feature.sale.domain.model.SaleWithOperation

interface SaleRepository {
    suspend fun insertSale(sale: Sale)
    suspend fun getSaleWithOperation(id: String): SaleWithOperation?
    suspend fun getLastVoucherOperationNo(): String
    suspend fun getAllSalesWithOperations(): List<SaleWithOperation>

}