package com.rgk.qhatu.data.audit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.data.audit.database.entity.AuditLogEntity
import com.rgk.qhatu.domain.common.SyncStats

@Dao
interface AuditLogDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: AuditLogEntity)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun update(entity: AuditLogEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: List<AuditLogEntity>)

    @Query("SELECT * FROM audit_logs")
    suspend fun fetchAll(): List<AuditLogEntity>

    @Query("SELECT COUNT(*) as count, MAX(fecha_sincronizado) as lastUpdated FROM audit_logs")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM audit_logs WHERE flag_sincronizado = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM audit_logs WHERE flag_sincronizado != 1")
    suspend fun deleteUnsynced()
}