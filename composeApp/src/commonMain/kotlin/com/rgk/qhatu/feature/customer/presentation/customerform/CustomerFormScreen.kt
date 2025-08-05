package com.rgk.qhatu.feature.customer.presentation.customerform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.loading.LoadingSection
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.customer.domain.model.Customer
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_delete_changes
import qhatuapp.composeapp.generated.resources.tx_global_save_changes
import qhatuapp.composeapp.generated.resources.tx_profile_customer_address
import qhatuapp.composeapp.generated.resources.tx_profile_customer_document_number
import qhatuapp.composeapp.generated.resources.tx_profile_customer_document_type
import qhatuapp.composeapp.generated.resources.tx_profile_customer_email
import qhatuapp.composeapp.generated.resources.tx_profile_customer_last_name_father
import qhatuapp.composeapp.generated.resources.tx_profile_customer_last_name_mother
import qhatuapp.composeapp.generated.resources.tx_profile_customer_name
import qhatuapp.composeapp.generated.resources.tx_profile_customer_phone

@Composable
fun CustomerFormScreen(
    isNew: Boolean = false,
    uiState: CustomerFormUiState,
    formState: CustomerFormValidationState,
    onFieldChange: (Customer.() -> Customer) -> Unit,
    onSaveClick: (Customer) -> Unit,
    onDeleteClick: (Customer) -> Unit,
    onBackPopUp: () -> Unit,
    onDeletePopUp: () -> Unit,
) {
    val fields = formState.fields


    when (uiState) {
        is CustomerFormUiState.Error -> {
            ErrorSection(uiState.message)
        }

        CustomerFormUiState.Loading -> {
            LoadingSection()
        }

        is CustomerFormUiState.Success -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth().padding(12.dp)
                    .verticalScroll(rememberScrollState())
                    .imePadding(),
            ) {
                CustomTextField(
                    value = fields.firstName.orEmpty(),
                    onValueChange = {
                        onFieldChange {
                            copy(firstName = it)
                        }
                    },
                    params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_profile_customer_name),
                        singleLine = true,
                        maxLength = 50
                    )
                )
                CustomTextField(
                    value = fields.lastName.orEmpty(),
                    onValueChange = {
                        onFieldChange {
                            copy(lastName = it)
                        }
                    },
                    params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_profile_customer_last_name_father),
                        singleLine = true,
                        maxLength = 50
                    )
                )

                CustomTextField(
                    value = fields.motherLastName.orEmpty(),
                    onValueChange = {
                        onFieldChange {
                            copy(motherLastName = it)
                        }
                    },
                    params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_profile_customer_last_name_mother),
                        singleLine = true,
                        maxLength = 50
                    )
                )

                CustomTextField(
                    value = fields.documentType,
                    onValueChange = {
                        onFieldChange {
                            copy(documentType = it)
                        }
                    },
                    params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_profile_customer_document_type),
                        singleLine = true,
                        maxLength = 10
                    )
                )

                CustomTextField(
                    value = fields.documentNumber,
                    onValueChange = {
                        onFieldChange {
                            copy(documentNumber = it)
                        }
                    },
                    params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_profile_customer_document_number),
                        singleLine = true,
                        maxLength = 10
                    )
                )

                CustomTextField(
                    value = fields.phoneNumber.orEmpty(),
                    onValueChange = {
                        onFieldChange {
                            copy(phoneNumber = it)
                        }
                    },
                    params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_profile_customer_phone),
                        singleLine = true,
                        maxLength = 12
                    )
                )

                CustomTextField(
                    value = fields.address.orEmpty(),
                    onValueChange = {
                        onFieldChange {
                            copy(address = it)
                        }
                    },
                    params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_profile_customer_address),
                        singleLine = true,
                        maxLength = 50
                    )
                )

                CustomTextField(
                    value = fields.email.orEmpty(),
                    onValueChange = {
                        onFieldChange {
                            copy(email = it)
                        }
                    },
                    params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_profile_customer_email),
                        singleLine = true,
                        maxLength = 50
                    )
                )

                if (isNew) {
                    ButtonActions(
                        modifier = Modifier.padding(12.dp),
                        primaryButtonText = stringResource(Res.string.tx_global_save_changes),
                        isEnabled = formState.isValid,
                        onPrimaryClick = {
                            onSaveClick(fields)
                        },
                    )
                } else {
                    ButtonActions(
                        modifier = Modifier.padding(12.dp),
                        primaryButtonText = stringResource(Res.string.tx_global_save_changes),
                        isEnabled = formState.isValid,
                        onPrimaryClick = {
                            onSaveClick(fields)
                        },
                        secondaryButtonText = stringResource(Res.string.tx_global_delete_changes),
                        onSecondaryClick = { onDeleteClick(fields.copy(isDeleted = true)) }
                    )
                }
            }
        }

        is CustomerFormUiState.SuccessUpsert -> {
            val isDeleted = uiState.isDeleted
            if (isDeleted) {
                onDeletePopUp()
            } else {
                onBackPopUp()
            }
        }

        CustomerFormUiState.Idle -> Unit
    }
}