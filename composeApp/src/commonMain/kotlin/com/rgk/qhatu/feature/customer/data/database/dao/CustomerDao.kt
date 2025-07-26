package com.rgk.qhatu.feature.customer.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.customer.data.database.entity.CustomerEntity
import com.rgk.qhatu.common.model.SyncStats

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: CustomerEntity)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun update(entity: CustomerEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: List<CustomerEntity>)

    @Query("SELECT COUNT(*) as count, MAX(fecha_actualizacion) as lastUpdated FROM clients")
    suspend fun getStats(): SyncStats

    @Query("SELECT * FROM clients")
    suspend fun fetchAll(): List<CustomerEntity>

    @Query("SELECT id FROM clients WHERE fecha_actualizacion = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM clients WHERE fecha_actualizacion != 1")
    suspend fun deleteUnsynced()

    @Query("SELECT * FROM clients WHERE nombre LIKE '%' || :query || '%' AND flagProveedor = 0")
    suspend fun fetchClient(query: String): List<CustomerEntity>

    @Query("SELECT * FROM clients WHERE razonSocial LIKE '%' || :query || '%' AND flagProveedor = 1")
    suspend fun fetchProvider(query: String): List<CustomerEntity>

}