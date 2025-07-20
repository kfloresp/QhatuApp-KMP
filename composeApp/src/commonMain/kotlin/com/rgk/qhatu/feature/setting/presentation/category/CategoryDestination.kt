package com.rgk.qhatu.feature.setting.presentation.category

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.navigation.ProvideAppBarActions
import com.rgk.qhatu.navigation.ProvideAppBarNavigationIcon
import com.rgk.qhatu.navigation.ProvideAppBarTitle
import com.rgk.qhatu.presentation.MainAction
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object CategoryDestination

internal fun NavGraphBuilder.categoryDestination(
    goBack: () -> Unit
) {
    composable<CategoryDestination> {
        val viewModel: CategoryViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        ProvideAppBarTitle {
            Text(
                text = "Configuration"
            )
        }
        ProvideAppBarNavigationIcon {
            IconButton(
                onClick = {
                    goBack()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }

        ProvideAppBarActions {
            IconButton(
                onClick = {
                    viewModel.syncCategories()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = null
                )
            }
        }

        CategoryScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChanged,
            onItemClick = viewModel::onItemClick,
            onEditClick = viewModel::onEditClick,
            onDeleteClick = viewModel::onDeleteClick,
        )
    }
}