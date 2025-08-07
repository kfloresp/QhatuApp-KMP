package com.rgk.qhatu.feature.payment.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.repository.CustomerRepository
import com.rgk.qhatu.feature.payment.domain.model.ClientPayment
import com.rgk.qhatu.feature.payment.domain.repository.ClientPaymentRepository

class GetCustomerPaymentsUseCase(private val repository: ClientPaymentRepository) {
    suspend operator fun invoke(): SyncResult<List<ClientPayment>> {
        return repository.fetchLocal()
    }
}