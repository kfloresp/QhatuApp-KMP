package com.rgk.qhatu.feature.setting.data.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.setting.data.database.entity.CategoryEntity
import com.rgk.qhatu.common.model.SyncStats

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: CategoryEntity) //UUID.randomUUID().toString()

    @Update
    suspend fun update(entity: CategoryEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: List<CategoryEntity>)

    @Query("SELECT * FROM categories where flag_eliminado = false")
    suspend fun fetchAll(): List<CategoryEntity>

    @Query("SELECT COUNT(*) as count, MAX(fecha_actualizacion) as lastUpdated FROM categories")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM categories WHERE flag_sincronizado = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM categories WHERE flag_sincronizado != 1")
    suspend fun deleteUnsynced()
}
