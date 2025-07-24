package com.rgk.qhatu.feature.setting.presentation.sync

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DownloadForOffline
import androidx.compose.material.icons.filled.PublishedWithChanges
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.feature.setting.presentation.sync.component.SyncType
import com.rgk.qhatu.navigation.ProvideAppBarActions
import kotlinx.serialization.Serializable

@Serializable
data object SyncDestination

internal fun NavGraphBuilder.syncDestination() {
    composable<SyncDestination> {

        ProvideAppBarActions {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Update, contentDescription = null)
            }
        }

        SyncScreen(onOptionClick = { syncType ->
            when (syncType) {
                SyncType.STORE -> {

                }

                SyncType.CATEGORY -> {

                }

                SyncType.BRAND -> {

                }

                SyncType.UNIT_MEASURE -> {

                }

                SyncType.AUDIT -> {

                }

                SyncType.CUSTOMER -> {

                }

                SyncType.PAYMENT_CUSTOMER -> {

                }

                SyncType.PAYMENT_TRANSACTION -> {

                }

                SyncType.PRODUCT -> {

                }

                SyncType.SALE -> {

                }

                SyncType.SALE_DETAIL -> {

                }

                SyncType.CONFIGURATION -> {

                }
            }
        })
    }
}