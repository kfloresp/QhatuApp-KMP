package com.rgk.qhatu.feature.audit.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.audit.domain.model.AuditLog

interface AuditLogRepository {
    suspend fun fetchLocal(): SyncResult<List<AuditLog>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<AuditLog>>
    suspend fun updateLocal(register: AuditLog): SyncResult<Unit>
    suspend fun saveLocal(registers: List<AuditLog>) : SyncResult<Unit>
    suspend fun syncLocalToRemote(): SyncResult<Unit>
    suspend fun syncRemoteToLocal(): SyncResult<Unit>
}