package com.rgk.qhatu.feature.customer.presentation.customerform.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.customer.domain.model.Company
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails
import com.rgk.qhatu.feature.customer.domain.model.updateCompany
import com.rgk.qhatu.feature.customer.domain.model.updateCustomer
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_delete_changes
import qhatuapp.composeapp.generated.resources.tx_global_save_changes
import qhatuapp.composeapp.generated.resources.tx_profile_customer_document_number
import qhatuapp.composeapp.generated.resources.tx_profile_customer_name

@Composable
fun CompanyFormContent(
    details: CustomerWithDetails.CompanyWithCustomer,
    isNew: Boolean,
    isValidForm: Boolean,
    onFieldChange: (CustomerWithDetails.() -> CustomerWithDetails) -> Unit,
    onSaveClick: (CustomerWithDetails) -> Unit,
    onDeleteClick: (CustomerWithDetails) -> Unit,
) {
    val company = details.company
    val customer = details.customer

    CustomTextField(
        value = company.companyName,
        onValueChange = { newValue ->
            onFieldChange {
                updateCompany {
                    it.copy(companyName = newValue)
                }
            }
        },
        params = CustomTextFieldParams(
            label = stringResource(Res.string.tx_profile_customer_name),
            singleLine = true,
            maxLength = 50
        )
    )

    CustomTextField(
        value = customer.documentNumber,
        onValueChange = { newValue ->
            onFieldChange {
                updateCustomer { it.copy(documentNumber = newValue) }
            }
        },
        params = CustomTextFieldParams(
            label = stringResource(Res.string.tx_profile_customer_document_number),
            singleLine = true,
            maxLength = 11
        )
    )

    ButtonActions(
        modifier = Modifier.padding(12.dp),
        primaryButtonText = stringResource(Res.string.tx_global_save_changes),
        isEnabled = isValidForm,
        onPrimaryClick = { onSaveClick(details) },
        secondaryButtonText = if (!isNew) stringResource(Res.string.tx_global_delete_changes) else null,
        onSecondaryClick = if (!isNew) {
            { onDeleteClick(details.copy(customer = customer.copy(isDeleted = true))) }
        } else null
    )
}


@Preview()
@Composable
private fun CompanyFormContentPreview() {
    MaterialTheme {
        CompanyFormContent(
            details = CustomerWithDetails.CompanyWithCustomer(
                company = Company(
                    customerId = "2",
                    companyName = "ACME Corp"
                ),
                customer = Customer(
                    customerId = "2",
                    documentNumber = "20123456789"
                )
            ),
            isNew = false,
            isValidForm = true,
            onFieldChange = {},
            onSaveClick = {},
            onDeleteClick = {}
        )
    }
}