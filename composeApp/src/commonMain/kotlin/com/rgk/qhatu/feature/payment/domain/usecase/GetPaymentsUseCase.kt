package com.rgk.qhatu.feature.payment.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.payment.domain.model.Payment
import com.rgk.qhatu.feature.payment.domain.repository.PaymentRepository

class GetPaymentsUseCase(private val repository: PaymentRepository) {
    suspend operator fun invoke(idPayment: String? = null): SyncResult<List<Payment>> {
        return repository.fetchLocal(idPayment)
    }
}