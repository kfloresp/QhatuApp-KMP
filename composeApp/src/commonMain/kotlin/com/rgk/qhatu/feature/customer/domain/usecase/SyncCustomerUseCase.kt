package com.rgk.qhatu.feature.customer.domain.usecase

import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.repository.CustomerRepository

class SyncCustomerUseCase(private val repository: CustomerRepository) {
    suspend operator fun invoke(operation: SyncOperation<Customer>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.SaveLocal -> repository.saveLocal(operation.registers)
            is SyncOperation.UpsertLocal -> repository.upsertLocal(operation.register)
            is SyncOperation.LocalToRemote -> repository.syncLocalToRemote()
            is SyncOperation.RemoteToLocal -> repository.syncRemoteToLocal()
        }
    }
}
