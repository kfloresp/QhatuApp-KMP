package com.rgk.qhatu.domain.mapper

import com.rgk.qhatu.data.product.database.entity.ProductEntity
import com.rgk.qhatu.data.product.remote.model.ProductModel
import com.rgk.qhatu.domain.model.Product

fun ProductModel.toDomain(): Product = Product(
    id = id,
    ean = ean,
    nombre = nombre,
    categoria_id = categoria_id,
    tipo_almacenamiento_id = tipo_almacenamiento_id,
    marca_id = marca_id,
    precio_unitario = precio_unitario,
    unidad_medida_id = unidad_medida_id,
    flag_lote = flag_lote,
    flag_activo = flag_activo,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
fun Product.toEntity(): ProductEntity = ProductEntity(
    idInternal = 0,
    id = id,
    ean = ean,
    nombre = nombre,
    categoria_id = categoria_id,
    tipo_almacenamiento_id = tipo_almacenamiento_id,
    marca_id = marca_id,
    precio_unitario = precio_unitario,
    unidad_medida_id = unidad_medida_id,
    flag_lote = flag_lote,
    flag_activo = flag_activo,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun ProductEntity.toDomain(): Product = Product(
    id = id,
    ean = ean,
    nombre = nombre,
    categoria_id = categoria_id,
    tipo_almacenamiento_id = tipo_almacenamiento_id,
    marca_id = marca_id,
    precio_unitario = precio_unitario,
    unidad_medida_id = unidad_medida_id,
    flag_lote = flag_lote,
    flag_activo = flag_activo,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun ProductEntity.toModel(): ProductModel = ProductModel(
    id = id,
    ean = ean,
    nombre = nombre,
    categoria_id = categoria_id,
    tipo_almacenamiento_id = tipo_almacenamiento_id,
    marca_id = marca_id,
    precio_unitario = precio_unitario,
    unidad_medida_id = unidad_medida_id,
    flag_lote = flag_lote,
    flag_activo = flag_activo,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun ProductModel.toEntity(): ProductEntity = ProductEntity(
    idInternal = 0,
    id = id,
    ean = ean,
    nombre = nombre,
    categoria_id = categoria_id,
    tipo_almacenamiento_id = tipo_almacenamiento_id,
    marca_id = marca_id,
    precio_unitario = precio_unitario,
    unidad_medida_id = unidad_medida_id,
    flag_lote = flag_lote,
    flag_activo = flag_activo,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
