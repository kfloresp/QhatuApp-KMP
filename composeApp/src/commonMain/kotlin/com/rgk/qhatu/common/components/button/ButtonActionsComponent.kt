package com.rgk.qhatu.common.components.button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonActionsRow(
    primaryButtonText: String,
    onPrimaryClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false,
    isLoading: Boolean = false,
    secondaryButtonText: String? = null,
    onSecondaryClick: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        if (secondaryButtonText != null && onSecondaryClick != null) {
            OutlinedButton(
                onClick = onSecondaryClick,
                modifier = Modifier.weight(1f),
                enabled = isEnabled && !isLoading
            ) {
                Text(secondaryButtonText)
            }
        }
        Button(
            onClick = onPrimaryClick,
            modifier = Modifier.weight(1f),
            enabled = isEnabled && !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(primaryButtonText)
            }
        }
    }
}

@Composable
fun ButtonActionsColumn(
    primaryButtonText: String,
    onPrimaryClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false,
    isLoading: Boolean = false,
    secondaryButtonText: String? = null,
    onSecondaryClick: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth().wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (secondaryButtonText != null && onSecondaryClick != null) {
            OutlinedButton(
                onClick = onSecondaryClick,
                enabled = !isLoading
            ) {
                Text(secondaryButtonText)
            }
        }
        Button(
            onClick = onPrimaryClick,
            enabled = isEnabled && !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(primaryButtonText)
            }
        }
    }
}
