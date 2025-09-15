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
import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails
import com.rgk.qhatu.feature.customer.presentation.customerform.component.CompanyFormContent
import com.rgk.qhatu.feature.customer.presentation.customerform.component.PersonFormContent
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
    isNew: Boolean,
    uiState: CustomerFormUiState,
    onFieldChange: (CustomerWithDetails.() -> CustomerWithDetails) -> Unit,
    onSaveClick: (CustomerWithDetails) -> Unit,
    onDeleteClick: (CustomerWithDetails) -> Unit,
) {
    when (uiState) {
        is CustomerFormUiState.Idle -> {
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
                when (details) {
                    is CustomerWithDetails.PersonWithCustomer -> {
                        PersonFormContent(
                            details = details,
                            isNew = isNew,
                            isValidForm = isValidForm,
                            onFieldChange = onFieldChange,
                            onSaveClick = onSaveClick,
                            onDeleteClick = onDeleteClick,
                        )
                    }

                    is CustomerWithDetails.CompanyWithCustomer -> {
                        CompanyFormContent(
                            details = details,
                            isNew = isNew,
                            isValidForm = isValidForm,
                            onFieldChange = onFieldChange,
                            onSaveClick = onSaveClick,
                            onDeleteClick = onDeleteClick,
                        )
                    }
                }
            }
        }
    }
}