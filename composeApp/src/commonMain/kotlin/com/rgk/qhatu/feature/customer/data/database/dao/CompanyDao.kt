package com.rgk.qhatu.feature.customer.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.rgk.qhatu.feature.customer.data.database.entity.CompanyEntity
import com.rgk.qhatu.feature.customer.data.database.entity.CompanyWithCustomerRelation

@Dao
interface CompanyDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertCompany(company: CompanyEntity)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun updateCompany(company: CompanyEntity)

    @Transaction
    @Query("SELECT * FROM customer_company WHERE customerId = :customerId")
    suspend fun getCompanyWithCustomerById(customerId: String): CompanyWithCustomerRelation?

    @Transaction
    @Query("SELECT * FROM customer_company")
    suspend fun getAllCompaniesWithCustomer(): List<CompanyWithCustomerRelation>
}