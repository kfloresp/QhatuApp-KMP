package com.rgk.qhatu.ui.components
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SyncItemCard(
    table: String,
    count: Int,
    lastUpdated: String,
    isSyncing: Boolean,
    onSyncClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = table, fontWeight = FontWeight.Bold)
            Text(text = "Registros: $count")
            Text(text = "Sincronizado: $lastUpdated")
        }

        if (isSyncing) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp
            )
        } else {
            IconButton(
                onClick = { onSyncClick() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Sync,
                    contentDescription = "Sincronizar"
                )
            }
        }
    }
}
