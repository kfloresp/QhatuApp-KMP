package com.rgk.qhatu.feature.payment.domain.usecase

import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.repository.CustomerRepository
import com.rgk.qhatu.feature.payment.domain.model.Payment
import com.rgk.qhatu.feature.payment.domain.repository.PaymentRepository

class SyncPaymentUseCase(
    private val repository: PaymentRepository,
    private val customerRepository: CustomerRepository,
) {
    suspend operator fun invoke(operation: SyncOperation<Payment>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.SaveLocal -> repository.saveLocal(operation.registers)
            is SyncOperation.UpsertLocal -> {
                repository.upsertLocal(operation.register)
//                customerRepository.updatePendingAmountLocal(
//                    operation.register.customerId,
//                    operation.register.amountPaid
//                )
            }

            is SyncOperation.LocalToRemote -> repository.syncLocalToRemote()
            is SyncOperation.RemoteToLocal -> repository.syncRemoteToLocal()
        }
    }
}
