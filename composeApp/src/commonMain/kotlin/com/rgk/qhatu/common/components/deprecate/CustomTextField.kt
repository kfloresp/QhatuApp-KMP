package com.rgk.qhatu.components.deprecate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_email
import qhatuapp.composeapp.generated.resources.tx_password
import qhatuapp.composeapp.generated.resources.tx_search

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    params: CustomTextFieldParams
) {
    Column(modifier = params.modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                if (it.length <= params.maxLength) onValueChange(it)
            },
            label = { Text(params.label) },
            isError = params.error != null,
            keyboardOptions = params.keyboardOptions,
            keyboardActions = params.keyboardActions,
            singleLine = params.singleLine,
            enabled = params.enabled,
            readOnly = params.readOnly || params.clickable,
            trailingIcon = params.trailingIcon,
            leadingIcon = params.leadingIcon,
            placeholder = params.placeholder?.let { { Text(it) } },
            modifier = Modifier
                .fillMaxWidth()
                .then(params.focusRequester?.let { Modifier.focusRequester(it) } ?: Modifier)
                .then(
                    if (params.clickable && params.onClick != null) {
                        Modifier.clickable(onClick = params.onClick)
                    } else Modifier
                ),
            shape = RoundedCornerShape(12.dp)
        )

        params.error?.let {
            Text(
                text = it,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
            )
        }
    }
}


@Composable
fun EmailField(
    value: String,
    error: String?,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    keyboardActions: KeyboardActions
) {
    val params = CustomTextFieldParams(
        label = stringResource(Res.string.tx_email),
        error = error,
        focusRequester = focusRequester,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        keyboardActions = keyboardActions,
        maxLength = 50
    )

    CustomTextField(
        value = value,
        onValueChange = onValueChange,
        params = params
    )
}


@Composable
fun PasswordField(
    value: String,
    error: String?,
    onValueChange: (String) -> Unit,
    visible: Boolean,
    onToggleVisibility: () -> Unit,
    focusRequester: FocusRequester,
    keyboardActions: KeyboardActions
) {
    val icon = if (visible) Icons.Default.Visibility else Icons.Default.VisibilityOff

    val params = CustomTextFieldParams(
        label = stringResource(Res.string.tx_password),
        error = error,
        focusRequester = focusRequester,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = keyboardActions,
        maxLength = 20,
        trailingIcon = {
            IconButton(onClick = onToggleVisibility) {
                Icon(imageVector = icon, contentDescription = null)
            }
        },
        singleLine = true
    )

    CustomTextField(
        value = value,
        onValueChange = onValueChange,
        params = params
    )
}


@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = stringResource(Res.string.tx_search)
) {
    val params = CustomTextFieldParams(
        label = "",
        placeholder = placeholder,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        )
    )

    CustomTextField(
        value = value,
        onValueChange = onValueChange,
        params = params
    )
}

data class CustomTextFieldParams(
    val label: String,
    val modifier: Modifier = Modifier,
    val error: String? = null,
    val focusRequester: FocusRequester? = null,
    val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    val keyboardActions: KeyboardActions = KeyboardActions.Default,
    val maxLength: Int = Int.MAX_VALUE,
    val singleLine: Boolean = true,
    val enabled: Boolean = true,
    val readOnly: Boolean = false,
    val clickable: Boolean = false,
    val onClick: (() -> Unit)? = null,
    val trailingIcon: @Composable (() -> Unit)? = null,
    val leadingIcon: @Composable (() -> Unit)? = null,
    val placeholder: String? = null
)




