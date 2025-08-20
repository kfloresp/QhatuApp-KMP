package com.rgk.qhatu.feature.product.presentation.product.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ProductActionSection(
    quantity: Int,
    onAddClick: () -> Unit,
    onRemoveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        when {
            quantity == 0 -> {
                IconButton(
                    onClick = onAddClick,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Agregar",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            else -> {
                IconButton(
                    onClick = onRemoveClick,
                ) {
                    Icon(
                        imageVector = if (quantity == 1) Icons.Default.Delete else Icons.Default.Remove,
                        contentDescription = if (quantity == 1) "Eliminar" else "Disminuir",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Text(
                    text = quantity.toString(),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.widthIn(min = 24.dp),
                    textAlign = TextAlign.Center
                )

                IconButton(
                    onClick = onAddClick,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Aumentar",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview()
@Composable
fun PreviewProductActionSection() {
    MaterialTheme {
        Column(Modifier.background(Color.White)) {
            ProductActionSection(
                quantity = 2,
                onAddClick = {},
                onRemoveClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            ProductActionSection(
                quantity = 1,
                onAddClick = {},
                onRemoveClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            ProductActionSection(
                quantity = 0,
                onAddClick = {},
                onRemoveClick = {}
            )
        }
    }
}