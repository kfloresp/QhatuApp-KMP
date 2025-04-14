package com.rgk.qhatu.ui.feature.sync

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.domain.usecase.SyncUseCase
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SyncViewModel(private val syncUseCase: SyncUseCase) : ViewModel() {

    private val _items = mutableStateListOf(
        SyncItem(SyncTable.PRODUCT, 0, "2025-04-09"),
        SyncItem(SyncTable.CLIENT, 0, "2025-04-08"),
        SyncItem(SyncTable.CONFIGURATION, 0, "2025-04-07")
    )
    val items: List<SyncItem> = _items


    fun syncTable(table: SyncTable) {
        val index = _items.indexOfFirst { it.table == table }
        if (index == -1) return

        viewModelScope.launch {
            _items[index]  = _items[index].copy(isSyncing = true)
            _items[index] = _items[index].copy(
                isSyncing = false,
                lastUpdated = "",
                count = getCountForTable(_items[index].table)
            )
        }
    }

    private suspend fun getCountForTable(table: SyncTable): Int {
        return when (table) {
            SyncTable.PRODUCT ->  syncUseCase.fetchProduct(table.collection).first().size
            SyncTable.CLIENT -> syncUseCase.fetchClient(table.collection).first().size
            SyncTable.CONFIGURATION -> syncUseCase.fetchConfiguration(table.collection).first().size
            else -> 0
        }
    }

    fun syncAllTables() {
        _items.forEach { item ->
            syncTable(item.table)
        }
    }
}

data class SyncItem(
    val table: SyncTable,
    val count: Int,
    val lastUpdated: String,
    val isSyncing: Boolean = false
)

enum class SyncTable(val collection: String, val label: String = "") {
    PRODUCT("producto","Productos"),
    CLIENT("cliente","Clientes"),
    CONFIGURATION("configuracion","Configuraciones"),
    TRANSACTION("Movimientos","Movimientos"),
    TRANSACTION_DETAIL("MovimientosDetalle"),
    BRAND("Marca"),
    CATEGORY("Categoria"),
    UNIT_MEASURE("UnidadMedida"),
    CLIENT_PAYMENT("ClientePago"),
    PAYMENT_TRANSACTION("PagoMovimiento"),
    AUDIT_LOG("Auditoria")
}

