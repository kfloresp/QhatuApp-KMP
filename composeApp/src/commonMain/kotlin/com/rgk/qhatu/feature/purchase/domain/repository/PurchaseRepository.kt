package com.rgk.qhatu.feature.purchase.domain.repository

import com.rgk.qhatu.feature.purchase.domain.model.Purchase
import com.rgk.qhatu.feature.purchase.domain.model.PurchaseWithOperation

interface PurchaseRepository {
    suspend fun insertPurchase(purchase: Purchase)
    suspend fun getPurchaseWithOperation(id: String): Purchase?
    suspend fun getAllPurchasesWithOperations(): List<PurchaseWithOperation>
}