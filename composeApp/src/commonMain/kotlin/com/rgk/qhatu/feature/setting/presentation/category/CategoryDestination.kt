package com.rgk.qhatu.feature.setting.presentation.category

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.feature.setting.domain.model.Category
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object CategoryDestination

internal fun NavGraphBuilder.categoryDestination(
) {
    composable<CategoryDestination> {
        val viewModel: CategoryViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()
        var selectedCategoryToEdit by remember { mutableStateOf<Category?>(null) }
        var selectedCategoryToDelete by remember { mutableStateOf<Category?>(null) }

        CategoryScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChanged,
            onItemClick = {
                selectedCategoryToEdit = it
            },
            onActionClick = {
                selectedCategoryToDelete = it
            },
            onPullRefresh = viewModel::onPullRefresh,
            isRefreshing = isRefreshing
        )

        selectedCategoryToEdit?.let { category ->
            ConfirmDialog(
                title = "¿Deseas editar la categoría \"${category.nombre}\"?",
                primaryButtonText = "Sí, editar",
                onPrimaryClick = {
                    selectedCategoryToEdit = null
                },
                secondaryButtonText = "Cancelar",
                onSecondaryClick = {
                    selectedCategoryToEdit = null
                },
                onDismiss = {
                    selectedCategoryToEdit = null
                }
            )
        }

        selectedCategoryToDelete?.let { category ->
            ConfirmDialog(
                title = "¿Deseas eliminar la categoría \"${category.nombre}\"?",
                primaryButtonText = "Sí, eliminar",
                onPrimaryClick = {
                    viewModel.onItemClick(category.copy(flag_eliminado = true))
                    selectedCategoryToDelete = null
                },
                secondaryButtonText = "Cancelar",
                onSecondaryClick = {
                    selectedCategoryToDelete = null
                },
                onDismiss = {
                    selectedCategoryToDelete = null
                }
            )
        }

    }
}