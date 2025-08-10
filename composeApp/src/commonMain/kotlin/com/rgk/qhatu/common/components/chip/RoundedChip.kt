package com.rgk.qhatu.common.components.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.rgk.qhatu.common.theme.QhatuTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RoundedChip(
    text: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    isSelected: Boolean = false,
    cornerRadius: Dp = 12.dp,
    horizontalPadding: Dp = 12.dp,
    verticalPadding: Dp = 8.dp,
    borderColor: Color = Color.LightGray,
    backgroundColor: Color = Color.Transparent,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    textSizeSp: Int = 14
) {
    val shape = RoundedCornerShape(cornerRadius)
    val clickableModifier = if (onClick != null) {
        modifier
            .clip(shape)
            .clickable(
                onClick = onClick,
                role = Role.Button,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    } else modifier.clip(shape)

    Surface(
        modifier = clickableModifier
            .padding(4.dp)
            .sizeIn(minWidth = 40.dp),
        shape = shape,
        color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.12f) else backgroundColor,
        border = BorderStroke(width = 1.dp, color = borderColor),
        shadowElevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = horizontalPadding, vertical = verticalPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = textColor,
                fontSize = textSizeSp.sp,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun RoundedRectChipPreview() {
    QhatuTheme {
        Column(modifier = Modifier.padding(16.dp).background(Color.White)) {
            RoundedChip(
                text = "Ejemplo normal",
                onClick = { /* do something */ }
            )
            RoundedChip(
                text = "Ejemplo seleccionado",
                isSelected = true,
                onClick = {}
            )
        }
    }
}