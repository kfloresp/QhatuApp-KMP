package com.rgk.qhatu.common.components.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.ic_question

@Composable
fun ConfirmDialog(
    title: String,
    description: String? = null,
    primaryButtonText: String,
    onPrimaryClick: () -> Unit,
    imagePainter: Painter? = null,
    secondaryButtonText: String? = null,
    onSecondaryClick: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null,
    isClosable: Boolean = false,
) {
    Dialog(onDismissRequest = { onDismiss?.invoke() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 8.dp,
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isClosable) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            modifier = Modifier.align(Alignment.TopEnd),
                            onClick = { onDismiss?.invoke() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null
                            )
                        }
                    }
                }

                imagePainter?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = it,
                        contentDescription = null,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                description?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(
                        8.dp,
                        Alignment.CenterVertically
                    )
                ) {

                    Button(onClick = onPrimaryClick, modifier = Modifier.fillMaxWidth()) {
                        Text(primaryButtonText)
                    }
                    if (secondaryButtonText != null && onSecondaryClick != null) {
                        OutlinedButton(
                            onClick = onSecondaryClick,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(secondaryButtonText)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ConfirmDialogPreview() {
    ConfirmDialog(
        title = "¿Desea eliminar (Categoria: LACTEOS)?",
        description = "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum",
        imagePainter = painterResource(resource = Res.drawable.ic_question),
        primaryButtonText = "Eliminar",
        onPrimaryClick = { /* acción */ },
        isClosable = true,
        secondaryButtonText = "Cancelar",
        onSecondaryClick = { /* cancelar */ },
        onDismiss = { /* cerrar modal */ }
    )
}