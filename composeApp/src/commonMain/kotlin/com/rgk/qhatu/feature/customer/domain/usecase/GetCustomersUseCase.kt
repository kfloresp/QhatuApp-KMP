package com.rgk.qhatu.feature.customer.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.repository.CustomerRepository

class GetCustomersUseCase(private val repository: CustomerRepository) {
    suspend operator fun invoke(idCustomer: String? = null): SyncResult<List<Customer>> {
        return repository.fetchLocal(idCustomer)
    }
}