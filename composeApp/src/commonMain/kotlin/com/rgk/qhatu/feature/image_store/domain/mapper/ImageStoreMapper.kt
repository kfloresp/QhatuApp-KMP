package com.rgk.qhatu.feature.image_store.domain.mapper

import com.rgk.qhatu.feature.image_store.data.database.entity.ImageStoreEntity
import com.rgk.qhatu.feature.image_store.domain.model.ImageStore
import com.rgk.qhatu.feature.image_store.domain.model.TableStore

fun ImageStoreEntity.toDomain(): ImageStore = ImageStore(
    id = id,
    entityId = entityId,
    tableStore = TableStore.valueOf(tableStore),
    filename = filename,
    syncedDate = syncedDate,
    isSynced = isSynced,
    lastUpdated = lastUpdated,
)

fun ImageStore.toEntity(): ImageStoreEntity = ImageStoreEntity(
    id = id,
    entityId = entityId,
    tableStore = tableStore.name,
    filename = filename,
    syncedDate = syncedDate,
    isSynced = isSynced,
    lastUpdated = lastUpdated,
)