package com.rgk.qhatu.feature.customer.presentation.customerform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.loading.LoadingSection
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.customer.domain.model.Customer

@Composable
fun CustomerFormScreen(
    uiState: CustomerFormUiState,
    isNew: Boolean = false,
    onSaveClick: (Customer) -> Unit,
    onDeleteClick: (Customer) -> Unit,
    onBackPopUp: () -> Unit,
) {
    when (uiState) {
        is CustomerFormUiState.Error -> {
            ErrorSection(uiState.message)
        }

        CustomerFormUiState.Loading -> {
            LoadingSection()
        }

        is CustomerFormUiState.Success -> {
            val result = uiState.result
            var firstName by remember { mutableStateOf(result.firstName ?: "") }
            var lastName by remember { mutableStateOf(result.lastName ?: "") }
            var motherLastName by remember { mutableStateOf(result.motherLastName ?: "") }
            var documentType by remember { mutableStateOf(result.documentType) }
            var documentNumber by remember { mutableStateOf(result.documentNumber) }
            var phoneNumber by remember { mutableStateOf(result.phoneNumber ?: "") }
            var address by remember { mutableStateOf(result.address ?: "") }
            var email by remember { mutableStateOf(result.email ?: "") }

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth().padding(12.dp).verticalScroll(rememberScrollState())
                    .imePadding(),
            ) {
                CustomTextField(
                    value = firstName, onValueChange = {
                        firstName = it
                    }, params = CustomTextFieldParams(
                        label = "Nombre",
                        singleLine = true,
                        maxLength = 50
                    )
                )

                CustomTextField(
                    value = lastName, onValueChange = {
                        lastName = it
                    }, params = CustomTextFieldParams(
                        label = "Apellido Paterno",
                        singleLine = true,
                        maxLength = 50
                    )
                )

                CustomTextField(
                    value = motherLastName, onValueChange = {
                        motherLastName = it
                    }, params = CustomTextFieldParams(
                        label = "Apellido Materno",
                        singleLine = true,
                        maxLength = 50
                    )
                )

                CustomTextField(
                    value = documentType, onValueChange = {
                        documentType = it
                    }, params = CustomTextFieldParams(
                        label = "Tipo Documento",
                        singleLine = true,
                        maxLength = 50
                    )
                )

                CustomTextField(
                    value = documentNumber, onValueChange = {
                        documentNumber = it
                    }, params = CustomTextFieldParams(
                        label = "Nro Documento",
                        singleLine = true,
                        maxLength = 50
                    )
                )

                CustomTextField(
                    value = phoneNumber, onValueChange = {
                        phoneNumber = it
                    }, params = CustomTextFieldParams(
                        label = "Celular",
                        singleLine = true,
                        maxLength = 50
                    )
                )

                CustomTextField(
                    value = address, onValueChange = {
                        address = it
                    }, params = CustomTextFieldParams(
                        label = "Dirección",
                        singleLine = true,
                        maxLength = 50
                    )
                )

                CustomTextField(
                    value = email, onValueChange = {
                        email = it
                    }, params = CustomTextFieldParams(
                        label = "Email",
                        singleLine = true,
                        maxLength = 50
                    )
                )

                if (isNew) {
                    ButtonActions(
                        modifier = Modifier.padding(12.dp),
                        primaryButtonText = "Guardar",
                        onPrimaryClick = { onSaveClick(result.copy(firstName = firstName,
                            lastName = lastName, motherLastName = motherLastName,
                            documentType = documentType, documentNumber = documentNumber,
                            phoneNumber = phoneNumber, address = address, email = email)) },
                    )
                } else {
                    ButtonActions(
                        modifier = Modifier.padding(12.dp),
                        primaryButtonText = "Guardar",
                        onPrimaryClick = { onSaveClick(result.copy(firstName = firstName,
                            lastName = lastName, motherLastName = motherLastName,
                            documentType = documentType, documentNumber = documentNumber,
                            phoneNumber = phoneNumber, address = address, email = email)) },
                        secondaryButtonText = "Eliminar",
                        onSecondaryClick = { onDeleteClick(result.copy(isDeleted = true)) }
                    )
                }
            }
        }

        CustomerFormUiState.SuccessUpsert -> onBackPopUp()
    }
}