package com.rgk.qhatu.feature.setting.presentation.store

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.PrimaryButton
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.imagepicker.ImagePicker
import com.rgk.qhatu.common.components.imagepicker.ImagePickerHandler
import com.rgk.qhatu.common.components.loading.LoadingSection
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.setting.domain.model.Store
import com.rgk.qhatu.shared.SharedImage
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_save_changes
import qhatuapp.composeapp.generated.resources.tx_setting_store_address
import qhatuapp.composeapp.generated.resources.tx_setting_store_company
import qhatuapp.composeapp.generated.resources.tx_setting_store_name
import qhatuapp.composeapp.generated.resources.tx_setting_store_phone
import qhatuapp.composeapp.generated.resources.tx_setting_store_ruc

@Composable
fun StoreScreen(
    uiState: StoreUiState,
    onFieldChange: (Store.() -> Store) -> Unit,
    onImageCaptured: (result: SharedImage) -> Unit,
    onSaveClick: (Store) -> Unit,
) {
    var showImagePicker by remember { mutableStateOf(false) }
    when (uiState) {
        is StoreUiState.Error -> {
            val error = uiState.message
            ErrorSection(error)
        }

        StoreUiState.Loading -> {
            LoadingSection()
        }

        is StoreUiState.Success -> {
            val field = uiState.result
            Column(
                Modifier.fillMaxSize()
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                CustomTextField(
                    value = field.commercialName.orEmpty(),
                    onValueChange = {
                        onFieldChange {
                            copy(commercialName = it)
                        }
                    }, params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_setting_store_name),
                        singleLine = true,
                        maxLength = 50
                    )
                )

                CustomTextField(
                    value = field.companyName.orEmpty(),
                    onValueChange = {
                        onFieldChange {
                            copy(companyName = it)
                        }
                    }, params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_setting_store_company),
                        singleLine = true,
                        maxLength = 50
                    )
                )

                CustomTextField(
                    value = field.ruc.orEmpty(),
                    onValueChange = {
                        onFieldChange {
                            copy(ruc = it)
                        }
                    }, params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_setting_store_ruc),
                        singleLine = true,
                        maxLength = 11
                    )
                )

                CustomTextField(
                    value = field.address.orEmpty(),
                    onValueChange = {
                        onFieldChange {
                            copy(address = it)
                        }
                    }, params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_setting_store_address),
                        singleLine = true,
                        maxLength = 100
                    )
                )

                CustomTextField(
                    value = field.phone.orEmpty(),
                    onValueChange = {
                        onFieldChange {
                            copy(phone = it)
                        }
                    }, params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_setting_store_phone),
                        singleLine = true,
                        maxLength = 12,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )
                )

                ImagePicker(
                    images = field.images,
                    onDeleteClick = { imageToDelete ->

                    }, onUploadClick = {
                        showImagePicker = true
                    },
                    title = "Agregar fotos",
                    description = "Muestra la facha de tu negocio",
                    buttonText = "Añadir fotos",
                    limitImages = 2
                )
                PrimaryButton(
                    stringResource(Res.string.tx_global_save_changes),
                    modifier = Modifier.padding(12.dp),
                    enabled = uiState.isValidForm,
                    onClick = {
                        onSaveClick(field)
                    })
            }
        }
    }
    ImagePickerHandler(
        showSourceOptionDialog = showImagePicker,
        onImageCaptured = { image ->
            onImageCaptured(image)
        },
        onDismiss = {
            showImagePicker = false
        }
    )
}