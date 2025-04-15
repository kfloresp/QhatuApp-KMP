package com.rgk.qhatu.domain.common

sealed class SyncTable(val collection: String, val label: String) {
    object Product : SyncTable("producto", "Productos")
    object Client : SyncTable("cliente", "Clientes")
    object Configuration : SyncTable("configuracion", "Configuraciones")
    object Transaction : SyncTable("movimiento", "Movimientos")
    object TransactionDetail : SyncTable("detallemovimiento", "Detalle Movimiento")
    object Brand : SyncTable("marca", "Marca")
    object Category : SyncTable("categoria", "Categoría")
    object UnitMeasure : SyncTable("unidadmedida", "Unidad de Medida")
    object ClientPayment : SyncTable("clientepago", "Pago Cliente")
    object PaymentTransaction : SyncTable("pagomovimiento", "Pago Movimiento")
    object AuditLog : SyncTable("auditoria", "Auditoría")

    companion object {
        val all = listOf(
            Product, Client, Configuration, Transaction,
            TransactionDetail, Brand, Category, UnitMeasure,
            ClientPayment, PaymentTransaction, AuditLog
        )
    }
}
