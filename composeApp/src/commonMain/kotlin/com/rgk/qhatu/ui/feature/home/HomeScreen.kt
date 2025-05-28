package com.rgk.qhatu.ui.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val itemHeight = 150.dp
    val horizontalSpacing = 12.dp
    val verticalSpacing = 12.dp
    val contentSpacing = PaddingValues(16.dp)

    val menuItems = viewModel.getMenuItems()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = contentSpacing,
        verticalArrangement = Arrangement.spacedBy(verticalSpacing),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing)
    ) {
        items(menuItems) { item ->
            MenuItemCard(
                params = MenuItemCardParams(
                    title = stringResource(item.title),
                    icon = item.icon,
                    backgroundColor = item.color,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight),
                    onClick = {
                        viewModel.onMenuItemClick(item, navController)
                    }
                )
            )
        }
    }
}
