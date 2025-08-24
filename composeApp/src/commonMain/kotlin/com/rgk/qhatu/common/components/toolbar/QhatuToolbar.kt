package com.rgk.qhatu.common.components.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.app_name
import qhatuapp.composeapp.generated.resources.ic_leaf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QhatuToolbar(
    title: String,
    showAppIcon: Boolean = false,
    showBackNavigation: Boolean = true,
    onBackNavigationClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Surface(
        shadowElevation = 2.dp
    ) {
        TopAppBar(
            modifier = modifier.fillMaxWidth(),
            navigationIcon = {
                if (showBackNavigation) {
                    onBackNavigationClick?.let {
                        IconButton(onClick = it) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                }
            },
            title = {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (showAppIcon) {
                        Image(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(Res.drawable.ic_leaf),
                            contentDescription = null,
                        )
                    }
                    if (title.isNotEmpty()) {
                        Text(
                            title,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    } else {
                        Text(
                            stringResource(Res.string.app_name),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            },
            actions = actions,
        )
    }
}
