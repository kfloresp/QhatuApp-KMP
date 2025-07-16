package com.rgk.qhatu.domain.feature.customer.mapper

import com.rgk.qhatu.data.feature.customer.database.entity.ClientEntity
import com.rgk.qhatu.data.feature.customer.remote.model.ClientModel
import com.rgk.qhatu.domain.feature.customer.model.Client

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