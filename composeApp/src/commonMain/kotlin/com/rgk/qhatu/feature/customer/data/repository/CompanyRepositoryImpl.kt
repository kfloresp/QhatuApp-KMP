package com.rgk.qhatu.feature.customer.data.repository

import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.customer.data.database.dao.CompanyDao
import com.rgk.qhatu.feature.customer.domain.mapper.toDomain
import com.rgk.qhatu.feature.customer.domain.mapper.toEntity
import com.rgk.qhatu.feature.customer.domain.model.Company
import com.rgk.qhatu.feature.customer.domain.model.CompanyWithCustomer
import com.rgk.qhatu.feature.customer.domain.repository.CompanyRepository

class CompanyRepositoryImpl(
    private val sourceLocal: CompanyDao,
) : CompanyRepository {

    override suspend fun getCompanyWithCustomer(customerId: String): CompanyWithCustomer? {
        return sourceLocal.getCompanyWithCustomerById(customerId)?.toDomain()
    }

    override suspend fun getAllCompaniesWithCustomer(): List<CompanyWithCustomer> {
        return sourceLocal.getAllCompaniesWithCustomer().map { it.toDomain() }
    }

    override suspend fun upsertCompany(
        company: Company,
        type: TypeUpsert,
    ) {
        when (type) {
            TypeUpsert.NEW -> {
                sourceLocal.insertCompany(company.toEntity())
            }
            TypeUpsert.UPDATE -> {
                sourceLocal.updateCompany(company.toEntity())
            }
        }
    }

    override suspend fun searchCompaniesWithCustomerByName(query: String): List<CompanyWithCustomer> {
        return sourceLocal.searchCompaniesWithCustomerByName(query).map { it.toDomain() }
    }
}