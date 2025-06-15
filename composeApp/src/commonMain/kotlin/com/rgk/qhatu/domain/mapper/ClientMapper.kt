package com.rgk.qhatu.domain.mapper

import com.rgk.qhatu.data.database.entity.ClientEntity
import com.rgk.qhatu.data.remote.model.ClientModel
import com.rgk.qhatu.domain.model.Client
import com.rgk.qhatu.domain.util.TimeUtils

fun ClientModel.toDomain(): Client = Client(
    id = id,
    nombre = nombre,
    razonSocial = razonSocial,
    apellidoPaterno = apellidoPaterno,
    apellidoMaterno = apellidoMaterno,
    tipoDocumento = tipoDocumento,
    numeroDocumento = numeroDocumento,
    celular = celular,
    direccion = direccion,
    correo = correo,
    saldoPendiente = saldoPendiente,
    flagProveedor = flagProveedor,
    flagActivo = flagActivo
)

fun Client.toEntity(): ClientEntity = ClientEntity(
    idInternal = 0,
    id = id,
    nombre = nombre,
    razonSocial = razonSocial,
    apellidoPaterno = apellidoPaterno,
    apellidoMaterno = apellidoMaterno,
    tipoDocumento = tipoDocumento,
    numeroDocumento = numeroDocumento,
    celular = celular,
    direccion = direccion,
    correo = correo,
    saldoPendiente = saldoPendiente,
    flagProveedor = flagProveedor,
    flagActivo = flagActivo,
    fechaSincronizado = fechaSincronizado,
    flagSincronizado = flagSincronizado
)

fun ClientEntity.toDomain(): Client = Client(
    id = id,
    nombre = nombre,
    razonSocial = razonSocial,
    apellidoPaterno = apellidoPaterno,
    apellidoMaterno = apellidoMaterno,
    tipoDocumento = tipoDocumento,
    numeroDocumento = numeroDocumento,
    celular = celular,
    direccion = direccion,
    correo = correo,
    saldoPendiente = saldoPendiente,
    flagProveedor = flagProveedor,
    flagActivo = flagActivo,
    fechaSincronizado = fechaSincronizado,
    flagSincronizado = flagSincronizado
)

fun ClientEntity.toModel(): ClientModel = ClientModel(
    id = id,
    nombre = nombre,
    apellidoPaterno = apellidoPaterno,
    apellidoMaterno = apellidoMaterno,
    tipoDocumento = tipoDocumento,
    numeroDocumento = numeroDocumento,
    celular = celular,
    direccion = direccion,
    correo = correo,
    saldoPendiente = saldoPendiente,
    flagProveedor = flagProveedor,
    flagActivo = flagActivo
)

fun ClientModel.toEntity(): ClientEntity = ClientEntity(
    idInternal = 0,
    id = id,
    nombre = nombre,
    apellidoPaterno = apellidoPaterno,
    apellidoMaterno = apellidoMaterno,
    tipoDocumento = tipoDocumento,
    numeroDocumento = numeroDocumento,
    celular = celular,
    direccion = direccion,
    correo = correo,
    saldoPendiente = saldoPendiente,
    flagProveedor = flagProveedor,
    flagActivo = flagActivo
)