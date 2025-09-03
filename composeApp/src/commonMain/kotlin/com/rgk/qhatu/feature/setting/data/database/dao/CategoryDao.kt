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
    suspend fun save(entity: CategoryEntity)

    @Update
    suspend fun update(entity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: List<CategoryEntity>)

    @Query("SELECT * FROM product_category where isDeleted = false")
    suspend fun fetchAll(): List<CategoryEntity>

    @Query("SELECT COUNT(*) as count, MAX(lastUpdated) as lastUpdated FROM product_category")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM product_category WHERE isSynced = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM product_category WHERE isSynced != 1")
    suspend fun deleteUnsynced()
}
