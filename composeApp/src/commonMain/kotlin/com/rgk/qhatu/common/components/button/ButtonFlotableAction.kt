package com.rgk.qhatu.common.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.rgk.qhatu.common.theme.QhatuTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ButtonFlotableAction(
    modifier: Modifier = Modifier,
    label: String,
    iconVector: ImageVector = Icons.Filled.Add,
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        icon = { Icon(iconVector, null) },
        text = { Text(text = label) }
    )
}

@Preview
@Composable
fun ButtonActionFlotablePreview() {
    QhatuTheme {
        Column(modifier = Modifier.background(Color.White)) {
            ButtonFlotableAction(modifier = Modifier, label = "Agregar") {

            }
        }
    }
}