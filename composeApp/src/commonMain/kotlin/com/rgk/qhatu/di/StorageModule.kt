package com.rgk.qhatu.di

import androidx.room.RoomDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

val databaseModule = module {
    single {
        get<RoomDatabase.Builder<AppDataBase>>().build()
    }
    dao { auditLogDao() }
    dao { brandDao() }
    dao { categoryDao() }
    dao { clientDao() }
    dao { clientPaymentDao() }
    dao { productDao() }
    dao { unitMeasureDao() }
    dao { storeDao() }
    dao { imageProductDao() }
    dao { cartDao() }
    dao { cartItemDao() }
    dao { operationDao() }
    dao { operationDetailDao() }
    dao { saleDao() }
    dao { purchaseDao() }
    dao { imageStoreDao() }
}

inline fun <reified T> Module.dao(
    crossinline daoProvider: AppDataBase.() -> T,
) {
    single<T> { get<AppDataBase>().daoProvider() }
}