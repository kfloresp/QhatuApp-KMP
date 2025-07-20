package com.rgk.qhatu.feature.setting.presentation.unitmeasure

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rgk.qhatu.common.components.list.ActionableListContent
import com.rgk.qhatu.common.components.search.SearchBar
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import com.rgk.qhatu.feature.setting.presentation.category.CategoryUiState

@Composable
fun UnitMeasureScreen(
    uiState: UnitMeasureUiState,
    onQueryChange: (String) -> Unit,
    onItemClick: (UnitMeasure) -> Unit,
    onEditClick: (UnitMeasure) -> Unit,
    onDeleteClick: (UnitMeasure) -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize()) {
        SearchBar(
            query = searchQuery,
            onQueryChange = {
                searchQuery = it
                onQueryChange(it)
            }
        )
        when (uiState) {
            is UnitMeasureUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is UnitMeasureUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = uiState.message, color = Color.Red)
                }
            }

            is UnitMeasureUiState.Success -> {
                ActionableListContent(
                    modifier = Modifier,
                    items = uiState.result,
                    itemToLabel = { it.nombre },
                    itemToKey = { it.id },
                    isSyncing = false,
                    onItemClick = onItemClick,
                    onEditClick = onEditClick,
                    onDeleteClick = onDeleteClick
                )
            }
        }
    }
}