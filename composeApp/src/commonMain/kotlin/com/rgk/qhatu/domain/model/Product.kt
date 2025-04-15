package com.rgk.qhatu.domain.model

data class Product(
    val id: String,
    val ean: String,
    val nombre: String,
    val categoria_id: String? = null,
    val tipo_almacenamiento_id: String? = null,
    val marca_id: String? = null,
    val precio_unitario: Double,
    val unidad_medida_id: String,
    val flag_lote: Int = 0,
    val flag_activo: Int = 1,
    val fecha_sincronizado: Long = 0,
    val flag_sincronizado: Int = 0
)