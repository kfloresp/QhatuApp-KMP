package com.rgk.qhatu.ui.feature.sync
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rgk.qhatu.domain.common.SyncTable
import com.rgk.qhatu.ui.components.AppToolbar
import com.rgk.qhatu.ui.components.SecondaryButton
import com.rgk.qhatu.ui.feature.home.HomeItem
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_back

@Composable
fun SyncScreen(
    navController: NavController
) {
    val scrollState = rememberScrollState()

    val syncAuditLogViewModel: SyncAuditLogViewModel = koinViewModel()
    val syncBrandViewModel: SyncBrandViewModel = koinViewModel()
    val syncCategoryViewModel: SyncCategoryViewModel = koinViewModel()
    val syncClientViewModel: SyncClientViewModel = koinViewModel()
    val syncClientPaymentViewModel: SyncClientPaymentViewModel = koinViewModel()
    val syncConfigurationViewModel: SyncConfigurationViewModel = koinViewModel()
    val syncPaymentTransactionViewModel: SyncPaymentTransactionViewModel = koinViewModel()
    val syncProductViewModel: SyncProductViewModel = koinViewModel()
    val syncTransactionViewModel: SyncTransactionViewModel = koinViewModel()
    val syncTransactionDetailViewModel: SyncTransactionDetailViewModel = koinViewModel()
    val syncUnitMeasureViewModel: SyncUnitMeasureViewModel = koinViewModel()

    val syncStateAuditLog by syncAuditLogViewModel.syncState.collectAsState()
    val syncStateBrand by syncBrandViewModel.syncState.collectAsState()
    val syncStateCategory by syncCategoryViewModel.syncState.collectAsState()
    val syncStateClient by syncClientViewModel.syncState.collectAsState()
    val syncStateClientPayment by syncClientPaymentViewModel.syncState.collectAsState()
    val syncStateConfiguration by syncConfigurationViewModel.syncState.collectAsState()
    val syncStatePaymentTransaction by syncPaymentTransactionViewModel.syncState.collectAsState()
    val syncStateProduct by syncProductViewModel.syncState.collectAsState()
    val syncStateTransaction by syncTransactionViewModel.syncState.collectAsState()
    val syncStateTransactionDetail by syncTransactionDetailViewModel.syncState.collectAsState()
    val syncStateUnitMeasure by syncUnitMeasureViewModel.syncState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        AppToolbar(title = stringResource(HomeItem.Sync.title),
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = stringResource(Res.string.tx_back)
                    )
                }
            },
            backgroundColor = Color(0xFF2196F3)
        )

        Column (modifier = Modifier.padding(start = 16.dp, end = 16.dp,top = 8.dp)) {

            SecondaryButton(
                text = "Sincronizar Todos",
                onClick = {
                    syncAuditLogViewModel.sync()
                    syncBrandViewModel.sync()
                    syncCategoryViewModel.sync()
                    syncClientViewModel.sync()
                    syncClientPaymentViewModel.sync()
                    syncConfigurationViewModel.sync()
                    syncPaymentTransactionViewModel.sync()
                    syncProductViewModel.sync()
                    syncTransactionViewModel.sync()
                    syncTransactionDetailViewModel.sync()
                    syncUnitMeasureViewModel.sync()
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            SyncItemCard(
                table = SyncTable.AuditLog.label,
                count = when (syncStateAuditLog) {
                    is SyncState.Success -> (syncStateAuditLog as SyncState.Success).count
                    else -> 0
                },
                lastUpdated = when (syncStateAuditLog) {
                    is SyncState.Success -> (syncStateAuditLog as SyncState.Success).lastUpdated
                    else -> "—"
                },
                isSyncing = syncStateAuditLog is SyncState.Loading,
                onSyncClick = { syncAuditLogViewModel.sync() }
            )

            if (syncStateAuditLog is SyncState.Error) {
                Text(
                    text = (syncStateAuditLog as SyncState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            SyncItemCard(
                table = SyncTable.Brand.label,
                count = when (syncStateBrand) {
                    is SyncState.Success -> (syncStateBrand as SyncState.Success).count
                    else -> 0
                },
                lastUpdated = when (syncStateBrand) {
                    is SyncState.Success -> (syncStateBrand as SyncState.Success).lastUpdated
                    else -> "—"
                },
                isSyncing = syncStateBrand is SyncState.Loading,
                onSyncClick = { syncBrandViewModel.sync() }
            )

            if (syncStateBrand is SyncState.Error) {
                Text(
                    text = (syncStateBrand as SyncState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            SyncItemCard(
                table = SyncTable.Category.label,
                count = when (syncStateCategory) {
                    is SyncState.Success -> (syncStateCategory as SyncState.Success).count
                    else -> 0
                },
                lastUpdated = when (syncStateCategory) {
                    is SyncState.Success -> (syncStateCategory as SyncState.Success).lastUpdated
                    else -> "—"
                },
                isSyncing = syncStateCategory is SyncState.Loading,
                onSyncClick = { syncCategoryViewModel.sync() }
            )

            if (syncStateCategory is SyncState.Error) {
                Text(
                    text = (syncStateCategory as SyncState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            SyncItemCard(
                table = SyncTable.Client.label,
                count = when (syncStateClient) {
                    is SyncState.Success -> (syncStateClient as SyncState.Success).count
                    else -> 0
                },
                lastUpdated = when (syncStateClient) {
                    is SyncState.Success -> (syncStateClient as SyncState.Success).lastUpdated
                    else -> "—"
                },
                isSyncing = syncStateClient is SyncState.Loading,
                onSyncClick = { syncClientViewModel.sync() }
            )

            if (syncStateClient is SyncState.Error) {
                Text(
                    text = (syncStateClient as SyncState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            SyncItemCard(
                table = SyncTable.ClientPayment.label,
                count = when (syncStateClientPayment) {
                    is SyncState.Success -> (syncStateClientPayment as SyncState.Success).count
                    else -> 0
                },
                lastUpdated = when (syncStateClientPayment) {
                    is SyncState.Success -> (syncStateClientPayment as SyncState.Success).lastUpdated
                    else -> "—"
                },
                isSyncing = syncStateClientPayment is SyncState.Loading,
                onSyncClick = { syncClientPaymentViewModel.sync() }
            )

            if (syncStateClientPayment is SyncState.Error) {
                Text(
                    text = (syncStateClientPayment as SyncState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            SyncItemCard(
                table = SyncTable.Configuration.label,
                count = when (syncStateConfiguration) {
                    is SyncState.Success -> (syncStateConfiguration as SyncState.Success).count
                    else -> 0
                },
                lastUpdated = when (syncStateConfiguration) {
                    is SyncState.Success -> (syncStateConfiguration as SyncState.Success).lastUpdated
                    else -> "—"
                },
                isSyncing = syncStateConfiguration is SyncState.Loading,
                onSyncClick = { syncConfigurationViewModel.sync() }
            )

            if (syncStateConfiguration is SyncState.Error) {
                Text(
                    text = (syncStateConfiguration as SyncState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            SyncItemCard(
                table = SyncTable.PaymentTransaction.label,
                count = when (syncStatePaymentTransaction) {
                    is SyncState.Success -> (syncStatePaymentTransaction as SyncState.Success).count
                    else -> 0
                },
                lastUpdated = when (syncStatePaymentTransaction) {
                    is SyncState.Success -> (syncStatePaymentTransaction as SyncState.Success).lastUpdated
                    else -> "—"
                },
                isSyncing = syncStatePaymentTransaction is SyncState.Loading,
                onSyncClick = { syncPaymentTransactionViewModel.sync() }
            )

            if (syncStatePaymentTransaction is SyncState.Error) {
                Text(
                    text = (syncStatePaymentTransaction as SyncState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            SyncItemCard(
                table = SyncTable.Product.label,
                count = when (syncStateProduct) {
                    is SyncState.Success -> (syncStateProduct as SyncState.Success).count
                    else -> 0
                },
                lastUpdated = when (syncStateProduct) {
                    is SyncState.Success -> (syncStateProduct as SyncState.Success).lastUpdated
                    else -> "—"
                },
                isSyncing = syncStateProduct is SyncState.Loading,
                onSyncClick = { syncProductViewModel.sync() }
            )

            if (syncStateProduct is SyncState.Error) {
                Text(
                    text = (syncStateProduct as SyncState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            SyncItemCard(
                table = SyncTable.Transaction.label,
                count = when (syncStateTransaction) {
                    is SyncState.Success -> (syncStateTransaction as SyncState.Success).count
                    else -> 0
                },
                lastUpdated = when (syncStateTransaction) {
                    is SyncState.Success -> (syncStateTransaction as SyncState.Success).lastUpdated
                    else -> "—"
                },
                isSyncing = syncStateTransaction is SyncState.Loading,
                onSyncClick = { syncTransactionViewModel.sync() }
            )

            if (syncStateTransaction is SyncState.Error) {
                Text(
                    text = (syncStateTransaction as SyncState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            SyncItemCard(
                table = SyncTable.TransactionDetail.label,
                count = when (syncStateTransactionDetail) {
                    is SyncState.Success -> (syncStateTransactionDetail as SyncState.Success).count
                    else -> 0
                },
                lastUpdated = when (syncStateTransactionDetail) {
                    is SyncState.Success -> (syncStateTransactionDetail as SyncState.Success).lastUpdated
                    else -> "—"
                },
                isSyncing = syncStateTransactionDetail is SyncState.Loading,
                onSyncClick = { syncTransactionDetailViewModel.sync() }
            )

            if (syncStateTransactionDetail is SyncState.Error) {
                Text(
                    text = (syncStateTransactionDetail as SyncState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            SyncItemCard(
                table = SyncTable.UnitMeasure.label,
                count = when (syncStateUnitMeasure) {
                    is SyncState.Success -> (syncStateUnitMeasure as SyncState.Success).count
                    else -> 0
                },
                lastUpdated = when (syncStateUnitMeasure) {
                    is SyncState.Success -> (syncStateUnitMeasure as SyncState.Success).lastUpdated
                    else -> "—"
                },
                isSyncing = syncStateUnitMeasure is SyncState.Loading,
                onSyncClick = { syncUnitMeasureViewModel.sync() }
            )

            if (syncStateUnitMeasure is SyncState.Error) {
                Text(
                    text = (syncStateUnitMeasure as SyncState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

sealed class SyncState {
    data object Idle : SyncState()
    data object Loading : SyncState()
    data class Success(val count: Int, val lastUpdated: String) : SyncState()
    data class Error(val message: String) : SyncState()
}

