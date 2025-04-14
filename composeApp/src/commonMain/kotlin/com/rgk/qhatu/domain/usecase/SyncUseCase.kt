package com.rgk.qhatu.domain.usecase

import com.rgk.qhatu.domain.model.AuditLog
import com.rgk.qhatu.domain.model.Brand
import com.rgk.qhatu.domain.model.Category
import com.rgk.qhatu.domain.model.Client
import com.rgk.qhatu.domain.model.ClientPayment
import com.rgk.qhatu.domain.model.Configuration
import com.rgk.qhatu.domain.model.PaymentTransaction
import com.rgk.qhatu.domain.model.Product
import com.rgk.qhatu.domain.model.Transaction
import com.rgk.qhatu.domain.model.TransactionDetail
import com.rgk.qhatu.domain.model.UnitMeasure
import com.rgk.qhatu.domain.repository.SyncRepository
import kotlinx.coroutines.flow.Flow

class SyncUseCase(private val repository: SyncRepository) {

    fun fetchConfiguration(collection: String): Flow<List<*>> {
        return repository.fetchCollection(collection, Configuration::class)
    }

    fun fetchProduct(collection: String): Flow<List<*>> {
        return repository.fetchCollection(collection, Product::class)
    }

    fun fetchClient(collection: String): Flow<List<*>> {
        return repository.fetchCollection(collection, Client::class)
    }
    fun fetchAuditLog(collection: String): Flow<List<*>> {
        return repository.fetchCollection(collection, AuditLog::class)
    }

    fun fetchBrand(collection: String): Flow<List<*>> {
        return repository.fetchCollection(collection, Brand::class)
    }
    
    fun fetchCategory(collection: String): Flow<List<*>> {
        return repository.fetchCollection(collection, Category::class)
    }
    
    fun fetchClientPayment(collection: String): Flow<List<*>> {
        return repository.fetchCollection(collection, ClientPayment::class)
    }
    
    fun fetchPaymentTransaction(collection: String): Flow<List<*>> {
        return repository.fetchCollection(collection, PaymentTransaction::class)
    }

    fun fetchTransaction(collection: String): Flow<List<*>> {
        return repository.fetchCollection(collection, Transaction::class)
    }

    fun fetchTransactionDetail(collection: String): Flow<List<*>> {
        return repository.fetchCollection(collection, TransactionDetail::class)
    }
    
    fun fetchUnitMeasure(collection: String): Flow<List<*>> {
        return repository.fetchCollection(collection, UnitMeasure::class)
    }
}