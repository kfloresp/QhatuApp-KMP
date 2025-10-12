package com.rgk.qhatu.feature.customer.domain.repository

import com.rgk.qhatu.feature.customer.domain.model.CustomerFlow

interface CustomerFlowRepository {
    suspend fun insertCustomerFlow(customer: CustomerFlow)
    suspend fun updateCustomerFlow(customer: CustomerFlow)
    suspend fun deleteCustomerFlow(customer: CustomerFlow)
    suspend fun getCustomerFlowById(customerId: String): CustomerFlow?
    suspend fun getAllCustomerFlows(): List<CustomerFlow>
}