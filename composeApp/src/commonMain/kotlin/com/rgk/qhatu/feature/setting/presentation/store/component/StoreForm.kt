package com.rgk.qhatu.feature.setting.presentation.store.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.PrimaryButton
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.setting.domain.model.Store
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_save_changes
import qhatuapp.composeapp.generated.resources.tx_setting_store_address
import qhatuapp.composeapp.generated.resources.tx_setting_store_name
import qhatuapp.composeapp.generated.resources.tx_setting_store_phone

@Composable
fun StoreForm(store: Store, onSaveClick: (Store) -> Unit) {
    var name by remember { mutableStateOf(store.companyName) }
    var address by remember { mutableStateOf(store.address.orEmpty()) }
    var phone by remember { mutableStateOf(store.phone.orEmpty()) }

    Column(Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(12.dp).weight(1f),
        ) {
            CustomTextField(
                value = name.orEmpty(), onValueChange = {
                    name = it
                }, params = CustomTextFieldParams(
                    label = stringResource(Res.string.tx_setting_store_name),
                    singleLine = true,
                    maxLength = 50
                )
            )

            CustomTextField(
                value = address, onValueChange = {
                    address = it
                }, params = CustomTextFieldParams(
                    label = stringResource(Res.string.tx_setting_store_address),
                    singleLine = true,
                    maxLength = 100
                )
            )

            CustomTextField(
                value = phone, onValueChange = {
                    phone = it
                }, params = CustomTextFieldParams(
                    label = stringResource(Res.string.tx_setting_store_phone),
                    singleLine = true,
                    maxLength = 12
                )
            )
//            ImagePicker(
//                images = fields.imageProduct,
//                onDeleteClick = { imageToDelete ->
//                    selectedImageProduct = imageToDelete
//                }, onUploadClick = {
//                    onImagePickerClick()
//                }
//            )
        }
        PrimaryButton(
            stringResource(Res.string.tx_global_save_changes),
            modifier = Modifier.padding(12.dp),
            onClick = { onSaveClick(store.copy(commercialName = name, address = address, phone = phone)) })
    }
}