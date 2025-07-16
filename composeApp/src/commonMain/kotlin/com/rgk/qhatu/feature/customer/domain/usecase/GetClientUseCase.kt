package com.rgk.qhatu.feature.customer.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.Client
import com.rgk.qhatu.feature.customer.domain.repository.ClientRepository

class GetClientUseCase(private val clientRepository: ClientRepository) {
    suspend operator fun invoke(query:String): SyncResult<List<Client>> {
        return clientRepository.fetchClient(query)
    }
}