package com.rgk.qhatu.feature.customer.presentation.customersummary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.list.ListContent
import com.rgk.qhatu.feature.customer.presentation.customer.component.ItemCustomerAction
import com.rgk.qhatu.feature.customer.presentation.customerprofile.CustomerProfileUiState
import com.rgk.qhatu.feature.customer.presentation.customersummary.component.CustomerListSkeleton
import com.rgk.qhatu.feature.customer.presentation.customersummary.component.CustomerSkeleton
import com.rgk.qhatu.feature.customer.presentation.customersummary.component.ItemSummary
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_back

@Composable
fun CustomerSummaryScreen(
    customerProfileUiState: CustomerProfileUiState,
    uiState: CustomerSummaryUiState,
    onBackPopUp: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        when (customerProfileUiState) {
            is CustomerProfileUiState.Error -> {
                //abrir popup de no carga y volver
            }

            CustomerProfileUiState.Loading -> {
                CustomerSkeleton()
            }

            is CustomerProfileUiState.Success -> {
                val customer = customerProfileUiState.result
//                ItemCustomerAction(
//                    title = customer.customerId,
//                    subTitle = customer.address.orEmpty(),
//                    firstLetter = "AS",
//                    onActionClick = {}
//                )

                SummarySection("ASDA")
            }
        }

        when (uiState) {
            is CustomerSummaryUiState.Error -> {
                ErrorSection(
                    uiState.message,
                    onClick = { onBackPopUp.invoke() },
                    buttonPrimary = Res.string.tx_global_back
                )
            }

            CustomerSummaryUiState.Loading -> {
                CustomerListSkeleton()
            }

            is CustomerSummaryUiState.Success -> {
                Column {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "Movimientos recientes",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
//                ListContent(
//                    items = uiState.result,
//                    itemKey = { "" }
//                ) {
//                    ItemSummary(title = it.type, date = it.date, amount = it.amount)
//                }
                }
            }

        }
    }
}

@Composable
fun SummarySection(amount: String) {
    Column(Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 16.dp),) {
        Text(
            modifier = Modifier,
            text = "Deuda actual",
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(4.dp))
        Text(
            modifier = Modifier,
            text = amount,
            fontSize = 24.sp,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}