package com.rgk.qhatu.feature.customer.data.repository

import com.rgk.qhatu.feature.customer.data.database.dao.CompanyDao
import com.rgk.qhatu.feature.customer.domain.mapper.toDomain
import com.rgk.qhatu.feature.customer.domain.mapper.toEntity
import com.rgk.qhatu.feature.customer.domain.model.Company
import com.rgk.qhatu.feature.customer.domain.model.CompanyWithCustomer
import com.rgk.qhatu.feature.customer.domain.repository.CompanyRepository

class CompanyRepositoryImpl(
    private val sourceLocal: CompanyDao,
) : CompanyRepository {
    override suspend fun insertCompany(company: Company) {
        sourceLocal.insertCompany(company.toEntity())
    }

    override suspend fun getCompanyWithCustomer(customerId: String): CompanyWithCustomer? {
        return sourceLocal.getCompanyWithCustomerById(customerId)?.toDomain()
    }

    override suspend fun getAllCompaniesWithCustomer(): List<CompanyWithCustomer> {
        return sourceLocal.getAllCompaniesWithCustomer().map { it.toDomain() }
    }
}