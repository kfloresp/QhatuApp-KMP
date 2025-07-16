package com.rgk.qhatu.feature.audit.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.audit.domain.repository.AuditLogRepository

class GetAuditLogStatsUseCase(private val auditLogRepository: AuditLogRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return auditLogRepository.getStats()
    }
}
