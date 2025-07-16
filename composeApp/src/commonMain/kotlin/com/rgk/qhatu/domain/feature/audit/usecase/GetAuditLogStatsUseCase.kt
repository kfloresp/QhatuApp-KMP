package com.rgk.qhatu.domain.feature.audit.usecase

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.feature.audit.repository.AuditLogRepository

class GetAuditLogStatsUseCase(private val auditLogRepository: AuditLogRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return auditLogRepository.getStats()
    }
}
