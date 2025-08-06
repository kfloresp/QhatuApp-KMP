package com.rgk.qhatu.feature.customer.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.CustomerSummary
import com.rgk.qhatu.feature.customer.domain.repository.CustomerRepository

class GetCustomerSummaryUseCase(private val repository: CustomerRepository) {
    suspend operator fun invoke(idCustomer: String): SyncResult<List<CustomerSummary>> {
        return repository.fetchSummary(idCustomer)
    }
}