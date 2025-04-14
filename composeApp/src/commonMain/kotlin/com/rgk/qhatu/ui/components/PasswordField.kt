package com.rgk.qhatu.ui.components
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp

@Composable
fun PasswordField(
    value: String,
    error: String?,
    showPassword: Boolean,
    onValueChange: (String) -> Unit,
    onTogglePassword: () -> Unit,
    focusRequester: FocusRequester,
    keyboardActions: KeyboardActions
) {
    val maxLength = 20
    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            if (newText.length <= maxLength) {
                onValueChange(newText)
            }
        },
        label = { Text("Contraseña") },
        isError = error != null,
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password,imeAction = ImeAction.Done),
        keyboardActions = keyboardActions,
        singleLine = true,
        modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
        trailingIcon = {
            IconButton(onClick = onTogglePassword) {
                Icon(
                    imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = if (showPassword) "Ocultar contraseña" else "Mostrar contraseña"
                )
            }
        }
    )
    error?.let {
        Box{
            Text(text = it, color = Color.Red, fontSize = 12.sp, modifier = Modifier.align(Alignment.Center) )
        }
    }
}
