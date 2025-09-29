package com.rgk.qhatu.feature.customer.presentation.customerform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.expandedsection.ExpandedSection
import com.rgk.qhatu.common.components.loading.LoadingSection
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails
import com.rgk.qhatu.feature.customer.domain.model.updateCompany
import com.rgk.qhatu.feature.customer.domain.model.updateCustomer
import com.rgk.qhatu.feature.customer.domain.model.updatePerson
import com.rgk.qhatu.feature.customer.presentation.customerform.extensions.markAsDeleted
import com.rgk.qhatu.feature.payment.presentation.paymentform.PATTERNS
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_checkout_amount_cash
import qhatuapp.composeapp.generated.resources.tx_global_delete_changes
import qhatuapp.composeapp.generated.resources.tx_global_save_changes
import qhatuapp.composeapp.generated.resources.tx_payment_currency_symbol
import qhatuapp.composeapp.generated.resources.tx_profile_customer_address
import qhatuapp.composeapp.generated.resources.tx_profile_customer_document_number
import qhatuapp.composeapp.generated.resources.tx_profile_customer_document_type
import qhatuapp.composeapp.generated.resources.tx_profile_customer_email
import qhatuapp.composeapp.generated.resources.tx_profile_customer_is_active
import qhatuapp.composeapp.generated.resources.tx_profile_customer_last_name_father
import qhatuapp.composeapp.generated.resources.tx_profile_customer_last_name_mother
import qhatuapp.composeapp.generated.resources.tx_profile_customer_name
import qhatuapp.composeapp.generated.resources.tx_profile_customer_phone
import qhatuapp.composeapp.generated.resources.tx_profile_customer_section_aditional
import qhatuapp.composeapp.generated.resources.tx_profile_customer_section_information
import qhatuapp.composeapp.generated.resources.tx_profile_customer_store_company

@Composable
fun CustomerFormScreen(
    isNew: Boolean,
    uiState: CustomerFormUiState,
    onFieldChange: (CustomerWithDetails.() -> CustomerWithDetails) -> Unit,
    onSaveClick: (CustomerWithDetails) -> Unit,
    onDeleteClick: (CustomerWithDetails) -> Unit,
    onBackPopUp: () -> Unit,
) {
    when (uiState) {
        is CustomerFormUiState.Success -> {
            onBackPopUp()
        }

        is CustomerFormUiState.Loading -> {
            LoadingSection()
        }

        is CustomerFormUiState.Error -> {
            ErrorSection(uiState.message)
        }

        is CustomerFormUiState.Upsert -> {
            val details = uiState.customerWithDetails
            val isValidForm = uiState.isValidForm

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState())
                    .imePadding(),
            ) {
                val customer = when (details) {
                    is CustomerWithDetails.PersonWithCustomer -> details.customer
                    is CustomerWithDetails.CompanyWithCustomer -> details.customer
                }

                CustomTextField(
                    value = customer.documentType.value,
                    onValueChange = {},
                    params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_profile_customer_document_type),
                        singleLine = true,
                        enabled = false
                    )
                )

                CustomTextField(
                    value = customer.documentNumber,
                    onValueChange = { newValue ->
                        onFieldChange { updateCustomer { it.copy(documentNumber = newValue) } }
                    },
                    params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_profile_customer_document_number),
                        singleLine = true,
                        maxLength = if (customer.isCompany) 11 else 10
                    )
                )
                when (details) {
                    is CustomerWithDetails.CompanyWithCustomer -> {
                        CustomTextField(
                            value = details.company.companyName,
                            onValueChange = { newValue ->
                                onFieldChange { updateCompany { it.copy(companyName = newValue) } }
                            },
                            params = CustomTextFieldParams(
                                label = stringResource(Res.string.tx_profile_customer_store_company),
                                singleLine = true,
                                maxLength = 50
                            )
                        )
                    }

                    is CustomerWithDetails.PersonWithCustomer -> {
                        CustomTextField(
                            value = details.person.firstName,
                            onValueChange = { newValue ->
                                onFieldChange { updatePerson { it.copy(firstName = newValue) } }
                            },
                            params = CustomTextFieldParams(
                                label = stringResource(Res.string.tx_profile_customer_name),
                                singleLine = true,
                                maxLength = 50
                            )
                        )
                        CustomTextField(
                            value = details.person.lastName,
                            onValueChange = { newValue ->
                                onFieldChange { updatePerson { it.copy(lastName = newValue) } }
                            },
                            params = CustomTextFieldParams(
                                label = stringResource(Res.string.tx_profile_customer_last_name_father),
                                singleLine = true,
                                maxLength = 50
                            )
                        )
                        CustomTextField(
                            value = details.person.motherLastName,
                            onValueChange = { newValue ->
                                onFieldChange { updatePerson { it.copy(motherLastName = newValue) } }
                            },
                            params = CustomTextFieldParams(
                                label = stringResource(Res.string.tx_profile_customer_last_name_mother),
                                singleLine = true,
                                maxLength = 50
                            )
                        )
                    }
                }

                ExpandedSection(titleSection = stringResource(Res.string.tx_profile_customer_section_information)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CustomTextField(
                            value = customer.phoneNumber.orEmpty(),
                            onValueChange = { newValue ->
                                onFieldChange {
                                    updateCustomer { it.copy(phoneNumber = newValue) }
                                }
                            },
                            params = CustomTextFieldParams(
                                label = stringResource(Res.string.tx_profile_customer_phone),
                                singleLine = true,
                                maxLength = 9
                            )
                        )

                        CustomTextField(
                            value = customer.address.orEmpty(),
                            onValueChange = { newValue ->
                                onFieldChange {
                                    updateCustomer { it.copy(address = newValue) }
                                }
                            },
                            params = CustomTextFieldParams(
                                label = stringResource(Res.string.tx_profile_customer_address),
                                singleLine = true,
                                maxLength = 50
                            )
                        )

                        CustomTextField(
                            value = customer.email.orEmpty(),
                            onValueChange = { newValue ->
                                onFieldChange {
                                    updateCustomer { it.copy(email = newValue) }
                                }
                            },
                            params = CustomTextFieldParams(
                                label = stringResource(Res.string.tx_profile_customer_email),
                                singleLine = true,
                                maxLength = 50
                            )
                        )
                    }
                }

                ExpandedSection(titleSection = stringResource(Res.string.tx_profile_customer_section_aditional)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CustomTextField(
                            value = customer.pendingAmountMax.orEmpty(),
                            onValueChange = { newValue ->
                                val regex = Regex(PATTERNS)
                                if (!newValue.matches(regex)) {
                                    return@CustomTextField
                                }
                                onFieldChange {
                                    updateCustomer { it.copy(pendingAmountMax = newValue) }
                                }
                            }, params = CustomTextFieldParams(
                                label = stringResource(Res.string.tx_checkout_amount_cash),
                                singleLine = true,
                                maxLength = 6,
                                leadingIcon = {
                                    Text(stringResource(Res.string.tx_payment_currency_symbol))
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                )
                            )
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                customer.isActive,
                                onCheckedChange = { newValue ->
                                    onFieldChange {
                                        updateCustomer { it.copy(isActive = newValue) }
                                    }
                                })
                            Text(stringResource(Res.string.tx_profile_customer_is_active))
                        }
                    }
                }

                ButtonActions(
                    modifier = Modifier.padding(12.dp),
                    primaryButtonText = stringResource(Res.string.tx_global_save_changes),
                    isEnabled = isValidForm,
                    onPrimaryClick = { onSaveClick(details) },
                    secondaryButtonText = if (!isNew) stringResource(Res.string.tx_global_delete_changes) else null,
                    onSecondaryClick = if (!isNew) {
                        { onDeleteClick(details.markAsDeleted()) }
                    } else null
                )

            }
        }
    }
}