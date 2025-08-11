package com.rgk.qhatu.feature.product.data.repository

import com.rgk.qhatu.common.exception.QhatuException
import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.feature.product.data.database.dao.ProductDao
import com.rgk.qhatu.feature.product.data.remote.ProductRemoteDataSource
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.product.domain.mapper.toDomain
import com.rgk.qhatu.feature.product.domain.mapper.toEntity
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.repository.ProductRepository
import com.rgk.qhatu.common.util.SearchType
import com.rgk.qhatu.utils.TimeUtils

class ProductRepositoryImpl(
    private val sourceRemote: ProductRemoteDataSource,
    private val sourceLocal: ProductDao
) : ProductRepository {
    override suspend fun fetchLocal(): SyncResult<List<Product>> {
        return try {
            val data = sourceLocal.fetchAll().map {
                it.toDomain()
            }
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun getStats(): SyncResult<SyncStats> {
        return try {
            val data = sourceLocal.getStats()
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun fetchRemote(): SyncResult<List<Product>> {
        return try {
            val data = sourceRemote.fetchCollection().map {
                it.toDomain()
            }
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun updateLocal(register: Product): SyncResult<Unit> {
        return safeCall {
            sourceLocal.update(register.toEntity())
        }
    }

    override suspend fun saveLocal(registers: List<Product>): SyncResult<Unit> {
        return safeCall {
            sourceLocal.save(registers.map { it.toEntity() })
        }
    }

    override suspend fun syncLocalToRemote(): SyncResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun syncRemoteToLocal(): SyncResult<Unit> {
        return safeCall {
            val localSyncedIds = sourceLocal.getSyncedIds()
            val remoteClients = sourceRemote.fetchCollection()
            sourceLocal.deleteUnsynced()
            val newClients = remoteClients.filterNot { it.id in localSyncedIds }
            sourceLocal.save(newClients.map {
                it.toEntity().copy(fecha_sincronizado = TimeUtils.getCurrentTimestamp())
            })
        }
    }

    override suspend fun getProductFromQuery(
        query: String,
        searchType: Int
    ): SyncResult<List<Product>> {
        return try{
            when (searchType) {
                SearchType.Ean.code ->{
                    val source = sourceLocal.queryByEan(query).map {
                        it.toDomain()
                    }
                    SyncResult.Success(source)
                }
                SearchType.Code.code -> {
                    val source = sourceLocal.queryByCode(query).map{
                        it.toDomain()
                    }
                    SyncResult.Success(source)
                }
                SearchType.Name.code -> {
                    val source = sourceLocal.queryByName(query).map {
                        it.toDomain()
                    }
                    SyncResult.Success(source)
                }

                else -> {
                    SyncResult.Error(QhatuException.Unexpected("Tipo de búsqueda no válida"))
                }
            }
        }catch (e: Exception){
            SyncResult.Error(e)
        }
    }

}