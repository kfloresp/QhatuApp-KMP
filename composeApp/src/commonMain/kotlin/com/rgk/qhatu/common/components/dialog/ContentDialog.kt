package com.rgk.qhatu.common.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.rgk.qhatu.components.deprecate.CustomTextField
import com.rgk.qhatu.components.deprecate.CustomTextFieldParams
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ContentDialog(
    title: String,
    content: @Composable ColumnScope.() -> Unit,
    primaryButtonText: String,
    onPrimaryClick: () -> Unit,
    secondaryButtonText: String? = null,
    onSecondaryClick: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null,
) {
    Dialog(onDismissRequest = { onDismiss?.invoke() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 8.dp,
            color = Color.White
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                content()
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    if (secondaryButtonText != null && onSecondaryClick != null) {
                        OutlinedButton(
                            onClick = onSecondaryClick, modifier = Modifier.weight(1f),
                        ) {
                            Text(secondaryButtonText)
                        }
                    }
                    Button(onClick = onPrimaryClick, modifier = Modifier.weight(1f)) {
                        Text(primaryButtonText)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ConfirmDialogPreview() {
    ContentDialog(
        title = "Editar Categoria",
        content = {
            Column(Modifier) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    CustomTextField(
                        value = "",
                        onValueChange = {},
                        params = CustomTextFieldParams(label = "Id")
                    )
                    CustomTextField(
                        value = "",
                        onValueChange = {},
                        params = CustomTextFieldParams(label = "Nombre")
                    )
                    CustomTextField(
                        value = "",
                        onValueChange = {},
                        params = CustomTextFieldParams(label = "Descripcion")
                    )
                }
            }
        },
        primaryButtonText = "Aceptar",
        onPrimaryClick = { /* acción */ },
        secondaryButtonText = "Cancelar",
        onSecondaryClick = { /* cancelar */ },
        onDismiss = { /* cerrar modal */ }
    )
}