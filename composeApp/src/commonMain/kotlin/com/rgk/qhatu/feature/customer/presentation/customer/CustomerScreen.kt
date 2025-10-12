package com.rgk.qhatu.feature.customer.presentation.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.empty.EmptySection
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.list.ActionableListContent
import com.rgk.qhatu.common.components.search.SearchBar
import com.rgk.qhatu.common.components.shimmer.ShimmerListVertical
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails
import com.rgk.qhatu.feature.customer.presentation.customer.component.ItemCustomerAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerScreen(
    uiState: CustomerUiState,
    onQueryChange: (String) -> Unit,
    onItemClick: (Customer) -> Unit,
    onActionClick: (Customer) -> Unit,
) {
    val query = if (uiState is CustomerUiState.Success) uiState.query else ""

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        when (uiState) {
            is CustomerUiState.Loading -> {
                ShimmerListVertical()
            }

            is CustomerUiState.Error -> {
                ErrorSection(uiState.message)
            }

            is CustomerUiState.Success -> {
                SearchBar(
                    query = query,
                    onQueryChange = onQueryChange,
                )
                ActionableListContent(
                    modifier = Modifier,
                    items = uiState.result,
                    itemKey = { customerWithDetails ->
                        when (customerWithDetails) {
                            is CustomerWithDetails.CompanyWithCustomer -> {
                                customerWithDetails.customer.customerId
                            }

                            is CustomerWithDetails.PersonWithCustomer -> {
                                customerWithDetails.customer.customerId
                            }
                        }
                    },
                    onItemClick = { customerWithDetails ->
                        when (customerWithDetails) {
                            is CustomerWithDetails.PersonWithCustomer -> {
                                onItemClick(customerWithDetails.customer)
                            }

                            is CustomerWithDetails.CompanyWithCustomer -> {
                                onItemClick(customerWithDetails.customer)
                            }
                        }
                    },
                    onActionClick = { customerWithDetails ->
                        when (customerWithDetails) {
                            is CustomerWithDetails.PersonWithCustomer -> {
                                onActionClick(customerWithDetails.customer)
                            }

                            is CustomerWithDetails.CompanyWithCustomer -> {
                                onActionClick(customerWithDetails.customer)
                            }
                        }
                    },
                    itemContent = { customerWithDetails, onClick, onAction ->
                        when (customerWithDetails) {
                            is CustomerWithDetails.CompanyWithCustomer -> {
                                ItemCustomerAction(
                                    title = customerWithDetails.company.fullName,
                                    subTitle = customerWithDetails.customer.pendingAmountCustomer,
                                    firstLetter = customerWithDetails.company.firstLetter,
                                    onItemClick = onClick,
                                    onActionClick = onAction
                                )
                            }

                            is CustomerWithDetails.PersonWithCustomer -> {
                                ItemCustomerAction(
                                    title = customerWithDetails.person.fullName,
                                    subTitle = customerWithDetails.customer.pendingAmountCustomer,
                                    firstLetter = customerWithDetails.person.firstLetter,
                                    onItemClick = onClick,
                                    onActionClick = onAction
                                )
                            }
                        }
                    }
                )
            }

            CustomerUiState.Empty -> {
                EmptySection()
            }
        }
    }
}