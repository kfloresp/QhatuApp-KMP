package com.rgk.qhatu.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable (() -> Unit))? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = Color.White,
    elevation: Dp = 4.dp
) {
    Surface(
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        color = backgroundColor,
        shape = RoundedCornerShape(12.dp),
        tonalElevation = elevation,
        shadowElevation = elevation
    ) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    color = contentColor,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            navigationIcon = {
                navigationIcon?.invoke()
            },
            actions = actions,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = backgroundColor,
                titleContentColor = contentColor,
                navigationIconContentColor = contentColor,
                actionIconContentColor = contentColor
            )
        )
    }
}
