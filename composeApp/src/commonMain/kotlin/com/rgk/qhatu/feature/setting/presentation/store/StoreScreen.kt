package com.rgk.qhatu.feature.setting.presentation.store

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.setting.domain.model.Store
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_setting_store_address
import qhatuapp.composeapp.generated.resources.tx_setting_store_name
import qhatuapp.composeapp.generated.resources.tx_setting_store_phone

@Composable
fun StoreScreen(
    store: Store,
    onStoreChange: (Store) -> Unit
) {
    var name by remember { mutableStateOf(store.name) }
    var address by remember { mutableStateOf(store.address.orEmpty()) }
    var phone by remember { mutableStateOf(store.phone.orEmpty()) }

    LaunchedEffect(name, address, phone) {
        onStoreChange(
            store.copy(
                name = name,
                address = address,
                phone = phone
            )
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize().padding(12.dp)
    ) {
        CustomTextField(
            value = name,
            onValueChange = {
                name = it
            },
            params = CustomTextFieldParams(
                label = stringResource(Res.string.tx_setting_store_name),
                singleLine = true,
                maxLength = 50
            )
        )

        CustomTextField(
            value = address,
            onValueChange = {
                address = it
            },
            params = CustomTextFieldParams(
                label = stringResource(Res.string.tx_setting_store_address),
                singleLine = true,
                maxLength = 100
            )
        )

        CustomTextField(
            value = phone,
            onValueChange = {
                phone = it
            },
            params = CustomTextFieldParams(
                label = stringResource(Res.string.tx_setting_store_phone),
                singleLine = true,
                maxLength = 12
            )
        )
    }
}