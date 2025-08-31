package com.rgk.qhatu.feature.purchase.data.repository

import com.rgk.qhatu.feature.purchase.data.database.dao.PurchaseDao
import com.rgk.qhatu.feature.purchase.domain.mapper.toDomain
import com.rgk.qhatu.feature.purchase.domain.mapper.toEntity
import com.rgk.qhatu.feature.purchase.domain.model.Purchase
import com.rgk.qhatu.feature.purchase.domain.model.PurchaseWithOperation
import com.rgk.qhatu.feature.purchase.domain.repository.PurchaseRepository

class PurchaseRepositoryImpl(private val purchaseDao: PurchaseDao) : PurchaseRepository {
    override suspend fun insertPurchase(purchase: Purchase) {
        purchaseDao.insertPurchase(purchase.toEntity())
    }

    override suspend fun getPurchaseWithOperation(id: String): Purchase? {
        return purchaseDao.getPurchaseWithOperation(id)?.toDomain()
    }

    override suspend fun getAllPurchasesWithOperations(): List<PurchaseWithOperation> {
        return purchaseDao.getAllPurchasesWithOperations().map { it.toDomain() }
    }
}