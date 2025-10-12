package com.rgk.qhatu.feature.customer.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.customer.data.database.entity.CustomerEntity

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertCustomer(entity: CustomerEntity)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun updateCustomer(entity: CustomerEntity)

    @Query("SELECT * FROM customer WHERE isDeleted = 0")
    suspend fun getAllCustomer(): List<CustomerEntity>

    @Query("SELECT * FROM customer WHERE isDeleted = 0 and customerId=:customerId")
    suspend fun getCustomerById(customerId: String): CustomerEntity?

    @Query("SELECT * FROM customer where isDeleted = 0 and pendingAmount > 0")
    suspend fun getCustomersWithDebt(): List<CustomerEntity>
}