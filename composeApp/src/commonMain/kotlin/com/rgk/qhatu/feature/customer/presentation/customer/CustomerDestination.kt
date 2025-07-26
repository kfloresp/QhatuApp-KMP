package com.rgk.qhatu.feature.customer.presentation.customer

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.button.ButtonFlotableAction
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.presentation.category.CategoryScreen
import com.rgk.qhatu.feature.setting.presentation.category.CategoryUiState
import com.rgk.qhatu.feature.setting.presentation.category.CategoryViewModel
import com.rgk.qhatu.navigation.ProvideFabAction
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_setting_add_new

@Serializable
data object CustomerDestination

internal fun NavGraphBuilder.customerDestination(
) {
    composable<CustomerDestination> {
        val viewModel: CustomerViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()

        ProvideFabAction {
            if (!isRefreshing && uiState is CustomerUiState.Success || uiState is CustomerUiState.Empty) {
                ButtonFlotableAction(
                    label = stringResource(Res.string.tx_setting_add_new)
                ) {

                }
            }
        }

        CustomerScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChanged,
            onItemClick = {

            },
            onActionClick = {

            },
            onPullRefresh = viewModel::onPullRefresh,
            isRefreshing = isRefreshing,
        )
    }
}