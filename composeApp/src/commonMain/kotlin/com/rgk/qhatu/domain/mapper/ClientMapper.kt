package com.rgk.qhatu.domain.mapper

import com.rgk.qhatu.data.database.entity.ClientEntity
import com.rgk.qhatu.data.remote.model.ClientModel
import com.rgk.qhatu.domain.model.Client

fun ClientModel.toDomain(): Client = Client(
    id = id,
    nombre = nombre,
    apellido_paterno = apellido_paterno,
    apellido_materno = apellido_materno,
    tipo_documento = tipo_documento,
    numero_documento = numero_documento,
    celular = celular,
    direccion = direccion,
    correo = correo,
    saldo_pendiente = saldo_pendiente,
    flag_proveedor = flag_proveedor,
    flag_activo = flag_activo,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun Client.toEntity(): ClientEntity = ClientEntity(
    idInternal = 0,
    id = id,
    nombre = nombre,
    apellido_paterno = apellido_paterno,
    apellido_materno = apellido_materno,
    tipo_documento = tipo_documento,
    numero_documento = numero_documento,
    celular = celular,
    direccion = direccion,
    correo = correo,
    saldo_pendiente = saldo_pendiente,
    flag_proveedor = flag_proveedor,
    flag_activo = flag_activo,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun ClientEntity.toDomain(): Client = Client(
    id = id,
    nombre = nombre,
    apellido_paterno = apellido_paterno,
    apellido_materno = apellido_materno,
    tipo_documento = tipo_documento,
    numero_documento = numero_documento,
    celular = celular,
    direccion = direccion,
    correo = correo,
    saldo_pendiente = saldo_pendiente,
    flag_proveedor = flag_proveedor,
    flag_activo = flag_activo,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun ClientEntity.toModel(): ClientModel = ClientModel(
    id = id,
    nombre = nombre,
    apellido_paterno = apellido_paterno,
    apellido_materno = apellido_materno,
    tipo_documento = tipo_documento,
    numero_documento = numero_documento,
    celular = celular,
    direccion = direccion,
    correo = correo,
    saldo_pendiente = saldo_pendiente,
    flag_proveedor = flag_proveedor,
    flag_activo = flag_activo,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun ClientModel.toEntity(): ClientEntity = ClientEntity(
    idInternal = 0,
    id = id,
    nombre = nombre,
    apellido_paterno = apellido_paterno,
    apellido_materno = apellido_materno,
    tipo_documento = tipo_documento,
    numero_documento = numero_documento,
    celular = celular,
    direccion = direccion,
    correo = correo,
    saldo_pendiente = saldo_pendiente,
    flag_proveedor = flag_proveedor,
    flag_activo = flag_activo,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)