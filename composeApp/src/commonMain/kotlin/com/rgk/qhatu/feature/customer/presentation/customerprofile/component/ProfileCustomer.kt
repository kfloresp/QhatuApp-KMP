package com.rgk.qhatu.feature.customer.presentation.customerprofile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.theme.QhatuTheme
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails
import com.rgk.qhatu.feature.customer.domain.model.DocumentType
import com.rgk.qhatu.feature.customer.domain.model.Person
import com.rgk.qhatu.feature.setting.presentation.setting.component.SettingItem
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_profile_customer_address
import qhatuapp.composeapp.generated.resources.tx_profile_customer_pending_balance
import qhatuapp.composeapp.generated.resources.tx_profile_customer_phone

@Composable
fun ProfileCustomer(
    customerWithDetails: CustomerWithDetails,
    onEditClick: (String, DocumentType) -> Unit,
    onResumeClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.weight(1f).padding(top = 40.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (customerWithDetails) {
                is CustomerWithDetails.CompanyWithCustomer -> {
                    val company = customerWithDetails.company
                    val customer = customerWithDetails.customer

                    ProfileEditCustomer(onClick = {
                        onEditClick(customer.customerId, customer.documentType)
                    })

                    Spacer(Modifier.height(10.dp))

                    Text(
                        company.fullName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        customer.documentNumber, style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.outline,
                    )

                    Spacer(Modifier.height(10.dp))

                    if (!customer.address.isNullOrEmpty()) {
                        SettingItem(
                            icon = Icons.Outlined.LocationOn,
                            title = stringResource(Res.string.tx_profile_customer_address),
                            subtitle = customer.address,
                        )
                    }
                    if (!customer.phoneNumber.isNullOrEmpty()) {
                        SettingItem(
                            icon = Icons.Outlined.Call,
                            title = stringResource(Res.string.tx_profile_customer_phone),
                            subtitle = customer.phoneNumber,
                        )
                    }
                    if (customer.havePendingAmount) {
                        SettingItem(
                            icon = Icons.Outlined.Payments,
                            title = stringResource(Res.string.tx_profile_customer_pending_balance),
                            subtitle = customer.pendingAmountCustomer,
                            onClick = { onResumeClick(customer.customerId) }
                        )
                    }
                }

                is CustomerWithDetails.PersonWithCustomer -> {
                    val person = customerWithDetails.person
                    val customer = customerWithDetails.customer

                    ProfileEditCustomer(onClick = {
                        onEditClick(customer.customerId, customer.documentType)
                    })

                    Text(
                        person.fullName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        customer.documentNumber, style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.outline,
                    )

                    Spacer(Modifier.height(10.dp))

                    if (!customer.address.isNullOrEmpty()) {
                        SettingItem(
                            icon = Icons.Outlined.LocationOn,
                            title = stringResource(Res.string.tx_profile_customer_address),
                            subtitle = customer.address,
                        )
                    }
                    if (!customer.phoneNumber.isNullOrEmpty()) {
                        SettingItem(
                            icon = Icons.Outlined.Call,
                            title = stringResource(Res.string.tx_profile_customer_phone),
                            subtitle = customer.phoneNumber,
                        )
                    }
                    if (customer.havePendingAmount) {
                        SettingItem(
                            icon = Icons.Outlined.Payments,
                            title = stringResource(Res.string.tx_profile_customer_pending_balance),
                            subtitle = customer.pendingAmountCustomer,
                            onClick = { onResumeClick(customer.customerId) }
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun ProfileCustomerPreview() {
    QhatuTheme {
        Column(Modifier.background(Color.White)) {
            ProfileCustomer(
                customerWithDetails = CustomerWithDetails.PersonWithCustomer(
                    person = Person(
                        firstName = "Kevin",
                        lastName = "Flores",
                        motherLastName = "Palomino"
                    ),
                    customer = Customer(
                        documentType = DocumentType.DNI,
                        documentNumber = "12345678",
                        phoneNumber = "987654321",
                        address = "Av. Siempre Viva 123",
                        pendingAmount = 150.0,
                        email = "kflores@gmail.com"
                    )
                ),
                onEditClick = { _, _ -> },
                onResumeClick = {},
            )
        }
    }
}