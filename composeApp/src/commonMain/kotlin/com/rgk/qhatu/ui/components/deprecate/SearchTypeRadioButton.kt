package com.rgk.qhatu.ui.components.deprecate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.ic_barcode

@Composable
fun <T> SearchTypeRadioButton(
    item: T,
    isSelected: Boolean,
    onSelected: (T) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { onSelected(item) }
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onSelected(item) }
        )
        Text(
            text = label,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}
@Composable
fun <T> SearchTypeSelector(
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    labelSelector: (T) -> String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEach { item ->
            SearchTypeRadioButton(
                item = item,
                isSelected = item == selectedItem,
                onSelected = onItemSelected,
                label = labelSelector(item)
            )
        }
    }
}
@Composable
fun SearchInputBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onScanClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.weight(1f),
            placeholder = { Text("Buscar por...") },
            singleLine = true,
            maxLines = 1,
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                onSearch()
            }),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
        onScanClick?.let {
            IconButton(onClick = {
                focusManager.clearFocus()
                it()
            }) {
                Icon(painterResource(Res.drawable.ic_barcode), contentDescription = null)
            }
        }
        IconButton(onClick = {
            focusManager.clearFocus()
            onSearch()
        }) {
            Icon(Icons.Default.Search, contentDescription = null)
        }
    }
}
