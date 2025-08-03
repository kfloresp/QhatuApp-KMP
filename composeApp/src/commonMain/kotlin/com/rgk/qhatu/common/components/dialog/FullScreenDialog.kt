package com.rgk.qhatu.common.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.rgk.qhatu.common.components.toolbar.QhatuToolbar
import com.rgk.qhatu.common.theme.QhatuTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FullScreenDialog(
    onDismiss: () -> Unit,
    title: String? = null,
    showCloseButton: Boolean = true,
    backgroundColor: Color = Color.White,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            shape = RectangleShape
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    title?.let {
                        QhatuToolbar(title = title, actions = {
                            if (showCloseButton) {
                                IconButton(onClick = onDismiss) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null
                                    )
                                }
                            }
                        })
                    }
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.surfaceContainer
                )
                Box(
                    modifier = Modifier.fillMaxSize().imePadding(),
                ) {
                    content()
                }
            }
        }
    }
}

@Preview
@Composable
private fun FullScreenDialogPreview() {
    QhatuTheme {
        Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {
            FullScreenDialog(
                onDismiss = { /* cerrar */ },
                backgroundColor = MaterialTheme.colorScheme.surface,
                title = "Formulario",
                showCloseButton = true
            ) {
                Column {
                    repeat(30) {
                        Text("Item #$it", modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
            }
        }
    }
}