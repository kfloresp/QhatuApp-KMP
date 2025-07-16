package com.rgk.qhatu.domain.feature.customer.usecase

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.feature.customer.model.Client
import com.rgk.qhatu.domain.feature.customer.repository.ClientRepository

class GetProviderUseCase(private val clientRepository: ClientRepository) {
    suspend operator fun invoke(query:String): SyncResult<List<Client>> {
        return clientRepository.fetchProvider(query)
    }
}