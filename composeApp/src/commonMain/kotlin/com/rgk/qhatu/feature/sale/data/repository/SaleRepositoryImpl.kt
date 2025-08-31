package com.rgk.qhatu.feature.sale.data.repository

import com.rgk.qhatu.feature.sale.data.database.dao.SaleDao
import com.rgk.qhatu.feature.sale.domain.mapper.toDomain
import com.rgk.qhatu.feature.sale.domain.mapper.toEntity
import com.rgk.qhatu.feature.sale.domain.model.Sale
import com.rgk.qhatu.feature.sale.domain.model.SaleWithOperation
import com.rgk.qhatu.feature.sale.domain.repository.SaleRepository

class SaleRepositoryImpl(private val saleDao: SaleDao) : SaleRepository {
    override suspend fun insertSale(sale: Sale) {
        saleDao.insertSale(sale.toEntity())
    }

    override suspend fun getSaleWithOperation(id: String): SaleWithOperation? {
        return saleDao.getSaleWithOperation(id)?.toDomain()
    }

    override suspend fun getLastVoucherOperationNo(): String? {
        return saleDao.getLastVoucherOperationNo()
    }

    override suspend fun getAllSalesWithOperations(): List<SaleWithOperation> {
        return saleDao.getAllSalesWithOperations().map { it.toDomain() }
    }
}