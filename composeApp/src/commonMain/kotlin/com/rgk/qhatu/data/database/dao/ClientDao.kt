package com.rgk.qhatu.data.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.data.database.entity.ClientEntity
import com.rgk.qhatu.domain.common.SyncStats

@Dao
interface ClientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: ClientEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: ClientEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: List<ClientEntity>)

    @Query("SELECT COUNT(*) as count, MAX(fecha_sincronizado) as lastUpdated FROM clients")
    suspend fun getStats(): SyncStats

    @Query("SELECT * FROM clients")
    suspend fun fetchAll(): List<ClientEntity>

    @Query("SELECT id FROM clients WHERE flag_sincronizado = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM clients WHERE flag_sincronizado != 1")
    suspend fun deleteUnsynced()

    @Query("SELECT * FROM clients WHERE nombre LIKE '%' || :query || '%' AND flag_proveedor = :provider")
    suspend fun fetchClientProvider(query: String, provider: Int): List<ClientEntity>

}
