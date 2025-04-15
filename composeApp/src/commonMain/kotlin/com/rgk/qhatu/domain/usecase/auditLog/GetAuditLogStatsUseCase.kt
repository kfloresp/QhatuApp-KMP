package com.rgk.qhatu.domain.usecase.auditLog

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.repository.AuditLogRepository

class GetAuditLogStatsUseCase(private val auditLogRepository: AuditLogRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return auditLogRepository.getStats()
    }
}
