package com.rgk.qhatu.common.components.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rgk.qhatu.common.theme.QhatuTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun <T> ChipGroup(
    items: List<T>,
    keySelector: (T) -> String,
    valueSelector: (T) -> String,
    selectedKey: String,
    errorText: String? = null,
    modifier: Modifier = Modifier,
    onChipClick: ((T) -> Unit)? = null,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        FlowRow {
            items.forEach { item ->
                RoundedChip(
                    text = valueSelector(item),
                    isSelected = keySelector(item) == selectedKey,
                    modifier = Modifier
                        .clickable { onChipClick?.invoke(item) }
                )
            }
        }
        Spacer(Modifier.height(4.dp))
        errorText?.let {
            Text(errorText, color = Color.Red, modifier = Modifier.padding(horizontal = 4.dp), fontSize = 14.sp)
        }
    }
}

private data class Example(val id: String, val name: String)

private val exampleList = listOf(
    Example("1", "Efectivo"),
    Example("2", "Tarjeta Credito"),
    Example("3", "Yape/Plin"),
    Example("4", "Crédito"),
)

@Preview
@Composable
private fun RoundedRectChipPreview() {
    QhatuTheme {
        Column(modifier = Modifier.padding(16.dp).background(Color.White).fillMaxSize()) {
            ChipGroup(
                items = exampleList,
                keySelector = { it.id },
                valueSelector = { it.name },
                selectedKey = "2",
                onChipClick = { method ->
                    println("Clicked: ${method.name}")
                },
                errorText = "Debes seleccionar una opción"
            )
        }
    }
}