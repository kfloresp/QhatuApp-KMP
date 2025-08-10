package com.rgk.qhatu.feature.customer.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.repository.CustomerRepository

class GetCustomersWithDebtUseCase(private val repository: CustomerRepository) {
    suspend operator fun invoke(
    ): SyncResult<List<Customer>> {
        return repository.fetchCustomerWithDebt()
    }
}