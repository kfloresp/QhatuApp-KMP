package com.rgk.qhatu.feature.customer.presentation.customerinformation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.shimmer.ShimmerListVertical
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.customer.domain.model.Customer

@Composable
fun CustomerInformationScreen(uiState: CustomerInformationUiState) {
    when (uiState) {
        is CustomerInformationUiState.Error -> {
            ErrorSection(uiState.message)
        }

        CustomerInformationUiState.Loading -> {
            ShimmerListVertical()
        }

        is CustomerInformationUiState.Success -> {
            val result: Customer = uiState.result

            var firstName by remember { mutableStateOf(result.firstName ?: "") }
            var lastName by remember { mutableStateOf(result.lastName ?: "") }
            var motherLastName by remember { mutableStateOf(result.motherLastName ?: "") }
            var documentType by remember { mutableStateOf(result.documentType) }
            var documentNumber by remember { mutableStateOf(result.documentNumber) }
            var phoneNumber by remember { mutableStateOf(result.phoneNumber ?: "") }
            var address by remember { mutableStateOf(result.address ?: "") }
            var email by remember { mutableStateOf(result.email ?: "") }
            var pendingAmount by remember { mutableStateOf(result.pendingAmount?.toString() ?: "") }

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(12.dp)
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

                CustomTextField(
                    value = pendingAmount, onValueChange = {
                        pendingAmount = it
                    }, params = CustomTextFieldParams(
                        label = "Saldo pendiente",
                        singleLine = true,
                        maxLength = 50
                    )
                )
            }
        }
    }
}