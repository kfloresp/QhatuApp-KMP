package com.rgk.qhatu.feature.setting.presentation.category.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_setting_cancel
import qhatuapp.composeapp.generated.resources.tx_setting_description
import qhatuapp.composeapp.generated.resources.tx_setting_name

@Composable
fun CategoryForm(
    initialName: String,
    initialDescription: String,
    onPrimaryButtonRes : StringResource,
    onConfirm: (String, String) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf(initialName) }
    var description by remember { mutableStateOf(initialDescription) }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        CustomTextField(
            value = name,
            onValueChange = { name = it },
            params = CustomTextFieldParams(
                label = stringResource(Res.string.tx_setting_name),
                singleLine = false,
                maxLength = 50
            )
        )
        CustomTextField(
            value = description,
            onValueChange = { description = it },
            params = CustomTextFieldParams(
                label = stringResource(Res.string.tx_setting_description),
                singleLine = false,
                maxLength = 50
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        ButtonActions(
            primaryButtonText = stringResource(onPrimaryButtonRes),
            onPrimaryClick = {
                onConfirm(name, description)
            },
            secondaryButtonText = stringResource(Res.string.tx_setting_cancel),
            onSecondaryClick = {
                onCancel()
            }
        )
    }
}
