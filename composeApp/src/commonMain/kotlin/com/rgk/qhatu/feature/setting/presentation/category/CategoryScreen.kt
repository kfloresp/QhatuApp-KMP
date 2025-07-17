package com.rgk.qhatu.feature.setting.presentation.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rgk.qhatu.common.components.list.ActionableListContent
import com.rgk.qhatu.common.theme.QhatuTheme
import com.rgk.qhatu.feature.setting.domain.model.Category
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CategoryScreen(
    uiState: CategoryUiState,
    onQueryChange: (String) -> Unit,
    onItemClick: (Category) -> Unit,
    onEditClick: (Category) -> Unit,
    onDeleteClick: (Category) -> Unit
) {
    ActionableListContent(
        modifier = Modifier,
        items = uiState.categories,
        itemToLabel = { it.nombre },
        itemToKey = { it.id },
        isSyncing = uiState.isSyncing,
        onQueryChange = onQueryChange,
        onItemClick = onItemClick,
        onEditClick = onEditClick,
        onDeleteClick = onDeleteClick
    )
}

@Preview
@Composable
private fun CategoryScreenPreview() {
    val sampleCategories = listOf(
        Category(id = "1", nombre = "Bebidas"),
        Category(id = "2", nombre = "Lácteos"),
        Category(id = "3", nombre = "Snacks")
    )

    val previewState = CategoryUiState(
        isSyncing = true,
        categories = sampleCategories,
        query = ""
    )

    QhatuTheme {
        Column(modifier = Modifier.background(Color.White)) {
            CategoryScreen(
                uiState = previewState,
                onQueryChange = {},
                onItemClick = {},
                onEditClick = {},
                onDeleteClick = {}
            )
        }
    }
}
