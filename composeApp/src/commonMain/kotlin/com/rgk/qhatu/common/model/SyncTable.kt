package com.rgk.qhatu.common.model

sealed class SyncTable(val collection: String, val label: String) {
    data object Product : SyncTable("producto", "Productos")
    data object Client : SyncTable("cliente", "Clientes")
    data object Configuration : SyncTable("configuracion", "Configuraciones")
    data object Transaction : SyncTable("movimiento", "Movimientos")
    data object TransactionDetail : SyncTable("detallemovimiento", "Detalle Movimiento")
    data object Brand : SyncTable("marca", "Marca")
    data object Category : SyncTable("categoria", "Categoría")
    data object UnitMeasure : SyncTable("unidadmedida", "Unidad de Medida")
    data object ClientPayment : SyncTable("clientepago", "Pago Cliente")
    data object PaymentTransaction : SyncTable("pagomovimiento", "Pago Movimiento")
    data object AuditLog : SyncTable("auditoria", "Auditoría")
    data object Store : SyncTable("tienda", "Auditoría")
}
