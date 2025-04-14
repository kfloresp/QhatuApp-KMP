package com.rgk.qhatu.ui.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.NewLabel
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Sync
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rgk.qhatu.ui.components.MenuItem
import com.rgk.qhatu.ui.components.MenuItemCard
import com.rgk.qhatu.ui.components.MenuItemCardParams

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val itemHeight = 150.dp
    val horizontalSpacing = 12.dp
    val verticalSpacing = 12.dp
    val contentSpacing = PaddingValues(16.dp)

    val menuItems = rememberMenuItems(navController)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = contentSpacing,
        verticalArrangement = Arrangement.spacedBy(verticalSpacing),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing)
    ) {
        items(menuItems) { item ->
            MenuItemCard(
                params = MenuItemCardParams(
                    title = item.title,
                    icon = item.icon,
                    backgroundColor = item.color,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight),
                    onClick = item.onClick
                )
            )
        }
    }
}

@Composable
fun rememberMenuItems(navController: NavController): List<MenuItem> {
    return remember(navController) {
        listOf(
            MenuItem(
                title = "Búsqueda",
                icon = Icons.Default.Search,
                color = Color(0xFF4CAF50),
                onClick = { }
            ),MenuItem(
                title = "Movimientos",
                icon = Icons.Default.Sync,
                color = Color(0xFF9C27B0),
                onClick = {  }
            ),
            MenuItem(
                title = "Ingreso",
                icon = Icons.Default.ArrowDownward,
                color = Color(0xFFF44336),
                onClick = { }
            ),
            MenuItem(
                title = "Salida",
                icon = Icons.Default.ArrowUpward,
                color = Color(0xFFFF9800),
                onClick = { }
            ),

            MenuItem(
                title = "Perfil",
                icon = Icons.Default.Person,
                color = Color(0xFF607D8B),
                onClick = { }
            ),
            MenuItem(
                title = "Sincronización",
                icon = Icons.Default.Sync,
                color = Color(0xFF2196F3),
                onClick = { navController.navigate("sync"){ popUpTo("home")} }
            ),
            MenuItem(
                title = "Configuración",
                icon = Icons.Default.Settings,
                color = Color(0xFF5C6BC0),
                onClick = {  }
            ),
            MenuItem(
                title = "Salir",
                icon = Icons.Default.Close,
                color = Color(0xFF795548),
                onClick = { navController.navigate("login") { popUpTo(0) } }
            )
        )
    }
}