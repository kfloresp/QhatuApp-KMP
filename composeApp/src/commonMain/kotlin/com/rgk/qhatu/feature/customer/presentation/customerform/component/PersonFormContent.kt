package com.rgk.qhatu.feature.customer.presentation.customerform.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails
import com.rgk.qhatu.feature.customer.domain.model.Person
import com.rgk.qhatu.feature.customer.domain.model.updateCustomer
import com.rgk.qhatu.feature.customer.domain.model.updatePerson
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_delete_changes
import qhatuapp.composeapp.generated.resources.tx_global_save_changes
import qhatuapp.composeapp.generated.resources.tx_profile_customer_document_number
import qhatuapp.composeapp.generated.resources.tx_profile_customer_last_name_father
import qhatuapp.composeapp.generated.resources.tx_profile_customer_last_name_mother
import qhatuapp.composeapp.generated.resources.tx_profile_customer_name

@Composable
fun PersonFormContent(
    details: CustomerWithDetails.PersonWithCustomer,
    isNew: Boolean,
    isValidForm: Boolean,
    onFieldChange: (CustomerWithDetails.() -> CustomerWithDetails) -> Unit,
    onSaveClick: (CustomerWithDetails) -> Unit,
    onDeleteClick: (CustomerWithDetails) -> Unit,
) {
    val person = details.person
    val customer = details.customer

    CustomTextField(
        value = person.firstName,
        onValueChange = { newValue ->
            onFieldChange {
                updatePerson {
                    it.copy(firstName = newValue)
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
        value = person.lastName,
        onValueChange = { newValue ->
            onFieldChange {
                updatePerson {
                    it.copy(lastName = newValue)
                }
            }
        },
        params = CustomTextFieldParams(
            label = stringResource(Res.string.tx_profile_customer_last_name_father),
            singleLine = true,
            maxLength = 50
        )
    )

    CustomTextField(
        value = person.motherLastName,
        onValueChange = { newValue ->
            onFieldChange {
                updatePerson {
                    it.copy(motherLastName = newValue)
                }
            }
        },
        params = CustomTextFieldParams(
            label = stringResource(Res.string.tx_profile_customer_last_name_mother),
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
            maxLength = 10
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
private fun PersonFormContentPreview() {
    MaterialTheme {
        PersonFormContent(
            details = CustomerWithDetails.PersonWithCustomer(
                person = Person(
                    firstName = "Juan",
                    lastName = "Pérez",
                    motherLastName = "García"
                ),
                customer = Customer(
                    customerId = "1",
                    documentNumber = "12345678"
                )
            ),
            isNew = true,
            isValidForm = true,
            onFieldChange = {},
            onSaveClick = {},
            onDeleteClick = {}
        )
    }
}