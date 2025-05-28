package com.rgk.qhatu.domain.usecase.client

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.model.Client
import com.rgk.qhatu.domain.repository.ClientRepository

class GetClientProviderUseCase(private val clientRepository: ClientRepository) {
    suspend operator fun invoke(query:String, isProvider:Int): SyncResult<List<Client>> {
        return clientRepository.fetchClientProvider(query, isProvider)
    }
}