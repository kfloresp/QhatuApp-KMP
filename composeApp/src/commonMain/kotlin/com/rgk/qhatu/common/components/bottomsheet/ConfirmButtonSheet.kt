package com.rgk.qhatu.common.components.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmBottomSheet(
    isVisible: Boolean,
    title: String,
    description: String? = null,
    primaryButtonText: String,
    onPrimaryClick: () -> Unit,
    imagePainter: Painter? = null,
    secondaryButtonText: String? = null,
    onSecondaryClick: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null,
    isClosable: Boolean = false,
    shape: Shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    scrimColor: Color = Color.Black.copy(alpha = 0.32f),
    containerColor: Color = MaterialTheme.colorScheme.surface,
    tonalElevation: Dp = 8.dp,
    extraContent: @Composable (() -> Unit)? = null
) {
    if (isVisible) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

        ModalBottomSheet(
            onDismissRequest = { onDismiss?.invoke() },
            sheetState = sheetState,
            shape = shape,
            scrimColor = scrimColor,
            containerColor = containerColor,
            tonalElevation = tonalElevation,
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
                                contentDescription = "Cerrar"
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

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )

                description?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }

                extraContent?.let {
                    Spacer(modifier = Modifier.height(12.dp))
                    it()
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
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


@Preview()
@Composable
fun PreviewConfirmBottomSheet() {
    MaterialTheme {
        ConfirmBottomSheet(
            isVisible = true,
            title = "Confirmar acción",
            description = "¿Estás seguro que deseas eliminar este elemento?",
            primaryButtonText = "Sí",
            onPrimaryClick = {}
        )
    }
}
