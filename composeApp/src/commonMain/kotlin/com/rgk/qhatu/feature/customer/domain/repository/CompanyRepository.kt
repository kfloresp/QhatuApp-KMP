package com.rgk.qhatu.feature.customer.domain.repository

import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.customer.domain.model.Company
import com.rgk.qhatu.feature.customer.domain.model.CompanyWithCustomer

interface CompanyRepository {
    suspend fun getCompanyWithCustomer(customerId: String): CompanyWithCustomer?
    suspend fun getAllCompaniesWithCustomer(): List<CompanyWithCustomer>
    suspend fun upsertCompany(company: Company, type: TypeUpsert)
    suspend fun searchCompaniesWithCustomerByName(query: String): List<CompanyWithCustomer>
}