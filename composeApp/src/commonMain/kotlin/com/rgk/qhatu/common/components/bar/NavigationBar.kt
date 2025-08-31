package com.rgk.qhatu.common.components.bar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

private val NAVIGATION_BAR_ITEM_HEIGHT_WIDTH = 64.dp

@Composable
fun QhatuBottomNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Column {
        HorizontalDivider()
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 0.dp,
            modifier = modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(NavigationBarDefaults.windowInsets)
                    .selectableGroup(),
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}

@Composable
fun RowScope.UnderlinedNavigationBarItem(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
    iconRes: DrawableResource,
    contentDescription: String,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
) {
    Column(modifier = Modifier
        .height(NAVIGATION_BAR_ITEM_HEIGHT_WIDTH)
        .weight(1f)) {

        this@UnderlinedNavigationBarItem.NavigationBarItem(
            title = title,
            selected = selected,
            onClick = onClick,
            iconRes = iconRes,
            contentDescription = contentDescription,
            enabled = enabled,
            alwaysShowLabel = alwaysShowLabel,
        )

        if (selected) {
            HorizontalDivider(
                thickness = 2.dp
            )
        }
    }
}

@Composable
fun RowScope.NavigationBarItem(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
    iconRes: DrawableResource,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
) {
    val currentTextStyle =
        if (selected) MaterialTheme.typography.titleSmall else MaterialTheme.typography.titleSmall

    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = title,
                style = currentTextStyle,
            )
        },
        icon = {
            Icon(
                painter = painterResource(resource = iconRes), contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
            disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
            indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        modifier = modifier.semantics {
            this.contentDescription = contentDescription
        },
        enabled = enabled,
        alwaysShowLabel = alwaysShowLabel,
    )
}