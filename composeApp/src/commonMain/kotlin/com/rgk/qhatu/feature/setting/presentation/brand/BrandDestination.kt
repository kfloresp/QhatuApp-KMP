package com.rgk.qhatu.feature.setting.presentation.brand

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.feature.setting.presentation.category.CategoryScreen
import com.rgk.qhatu.feature.setting.presentation.category.CategoryViewModel
import com.rgk.qhatu.navigation.ProvideAppBarActions
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object BrandDestination

internal fun NavGraphBuilder.brandDestination(
) {
    composable<BrandDestination> {
        val viewModel: BrandViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()


        ProvideAppBarActions {
            IconButton(
                onClick = {
                    viewModel.fetchRemote()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = null
                )
            }
        }

        BrandScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChanged,
            onItemClick = viewModel::onItemClick,
            onEditClick = viewModel::onEditClick,
            onDeleteClick = viewModel::onDeleteClick,
        )
    }
}