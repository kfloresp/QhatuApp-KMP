package com.rgk.qhatu.common.components.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.empty.EmptySection
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.common.components.textfield.SearchField

@Composable
fun <T> SearchContent(
    modifier: Modifier = Modifier,
    items: List<T>,
    keySelector: (T) -> String,
    valueSelector: (T) -> String,
    onSearch: (String) -> Unit,
    onSelectItem: (T) -> Unit
) {
    var query by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxWidth()) {
        SearchField(
            value = query,
            onValueChange = {
                query = it
                onSearch(it)
            }
        )

        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            when {
                query.isBlank() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SearchSection(width = 200.dp, height = 200.dp)
                    }
                }
                items.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        EmptySection(width = 200.dp, height = 200.dp)
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(items, key = { keySelector(it) }) { item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onSelectItem(item) }
                                    .padding(horizontal = 16.dp, vertical = 12.dp)
                            ) {
                                Text(
                                    text = valueSelector(item),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
