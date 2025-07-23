package com.rgk.qhatu.feature.setting.presentation.brand

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
import com.rgk.qhatu.feature.setting.domain.model.Brand

@Composable
fun BrandScreen(
    uiState: BrandUiState,
    onQueryChange: (String) -> Unit,
    onItemClick: (Brand) -> Unit,
    onActionClick: (Brand) -> Unit,
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
            is BrandUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is BrandUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = uiState.message, color = Color.Red)
                }
            }

            is BrandUiState.Success -> {
                ActionableListContent(
                    modifier = Modifier,
                    items = uiState.result,
                    itemToLabel = { it.name },
                    itemToKey = { it.id },
                    onItemClick = onItemClick,
                    onActionClick = onActionClick,
                )
            }
        }
    }
}