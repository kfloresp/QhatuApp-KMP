package com.rgk.qhatu.data.feature.product.repository

import com.rgk.qhatu.data.feature.product.database.dao.ProductDao
import com.rgk.qhatu.data.feature.product.remote.ProductRemoteDataSource
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.feature.product.mapper.toDomain
import com.rgk.qhatu.domain.feature.product.mapper.toEntity
import com.rgk.qhatu.domain.feature.product.mapper.toModel
import com.rgk.qhatu.domain.feature.product.model.Product
import com.rgk.qhatu.domain.feature.product.repository.ProductRepository
import com.rgk.qhatu.domain.util.TimeUtils
import com.rgk.qhatu.ui.feature.search.SearchType
import com.rgk.qhatu.utils.QhatuException

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

    override suspend fun uploadRemote(): SyncResult<Boolean> {
        return try {
            val data = sourceLocal.fetchAll().map {
                it.toModel()
            }
            data.forEach {
                sourceRemote.uploadCollection(it)
            }
            SyncResult.Success(true)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun updateLocal(register: Product): SyncResult<Boolean> {
        return try {
            sourceLocal.update(register.toEntity())
            SyncResult.Success(true)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun saveLocal(registers: List<Product>): SyncResult<Boolean> {
        return try {
            sourceLocal.save(registers.map { it.toEntity() })
            SyncResult.Success(true)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun syncLocalToRemote(): SyncResult<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun syncRemoteToLocal(): SyncResult<Boolean> {
        return try {
            val localSyncedIds = sourceLocal.getSyncedIds()
            val remoteClients = sourceRemote.fetchCollection()
            sourceLocal.deleteUnsynced()
            val newClients = remoteClients.filterNot { it.id in localSyncedIds }
            sourceLocal.save(newClients.map {
                it.toEntity().copy(fecha_sincronizado = TimeUtils.getCurrentTimestamp())
            })
            SyncResult.Success(true)
        } catch (e: Exception) {
            SyncResult.Error(e)
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