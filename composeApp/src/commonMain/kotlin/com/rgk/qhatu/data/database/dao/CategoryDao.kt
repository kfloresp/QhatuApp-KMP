package com.rgk.qhatu.data.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.data.database.entity.BrandEntity
import com.rgk.qhatu.data.database.entity.CategoryEntity
import com.rgk.qhatu.domain.common.SyncStats
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: CategoryEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: List<CategoryEntity>)

    @Query("SELECT * FROM categories")
    suspend fun fetchAll(): List<CategoryEntity>

    @Query("SELECT COUNT(*) as count, MAX(fecha_actualizacion) as lastUpdated FROM categories")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM categories WHERE flag_sincronizado = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM categories WHERE flag_sincronizado != 1")
    suspend fun deleteUnsynced()
}
