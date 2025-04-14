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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rgk.qhatu.di.AppModule
import com.rgk.qhatu.ui.components.AppToolbar
import com.rgk.qhatu.ui.components.OutlineButton
import com.rgk.qhatu.ui.components.SyncItemCard

@Composable
fun SyncScreen(
    navController: NavController,
    viewModel: SyncViewModel = AppModule.syncViewModel
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        AppToolbar(title = "Sincronización",
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Volver"
                    )
                }
            },
            backgroundColor = Color(0xFF2196F3)
        )

        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp,top = 8.dp)) {

            OutlineButton(
                text = "Sincronizar Todos",
                onClick = { viewModel.syncAllTables() }
            )

            Spacer(modifier = Modifier.height(8.dp))

            viewModel.items.forEach { item ->
                SyncItemCard(
                    item = item,
                    onSyncClick = { viewModel.syncTable(item.table) }
                )
            }
        }
    }
}
