package com.rgk.qhatu.feature.setting.presentation.brand

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.chip.ChipGroup
import com.rgk.qhatu.common.components.empty.EmptySection
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.search.SearchBar
import com.rgk.qhatu.common.components.search.SearchMode
import com.rgk.qhatu.common.components.skeleton.ShimmerChipsSkeleton
import com.rgk.qhatu.feature.setting.domain.model.Brand

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandScreen(
    uiState: BrandUiState,
    onQueryChange: (String) -> Unit,
    onActionClick: (Brand) -> Unit,
) {
    val query = if (uiState is BrandUiState.Success) uiState.query else ""

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        when (uiState) {
            is BrandUiState.Loading -> {
                ShimmerChipsSkeleton()
            }

            is BrandUiState.Error -> {
                ErrorSection(uiState.message)
            }

            is BrandUiState.Success -> {
                SearchBar(
                    query = query,
                    searchMode = SearchMode.Input,
                    onQueryChange = onQueryChange
                )
                ChipGroup(
                    items = uiState.result,
                    keySelector = { it.id },
                    valueSelector = { it.name },
                    selectedKey = "",
                    showIcon = true,
                    onChipClick = { item ->
                        onActionClick(item)
                    },
                )
            }

            BrandUiState.Empty -> {
                EmptySection()
            }
        }
    }
}