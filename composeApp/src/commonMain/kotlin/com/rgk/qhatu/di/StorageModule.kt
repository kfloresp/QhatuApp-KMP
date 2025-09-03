package com.rgk.qhatu.di

import androidx.room.RoomDatabase
import com.rgk.qhatu.feature.audit.data.database.dao.AuditLogDao
import com.rgk.qhatu.feature.cart.data.database.dao.CartDao
import com.rgk.qhatu.feature.cart.data.database.dao.CartItemDao
import com.rgk.qhatu.feature.customer.data.database.dao.CustomerDao
import com.rgk.qhatu.feature.payment.data.database.dao.PaymentDao
import com.rgk.qhatu.feature.product.data.database.dao.ImageProductDao
import com.rgk.qhatu.feature.product.data.database.dao.ProductDao
import com.rgk.qhatu.feature.setting.data.database.dao.BrandDao
import com.rgk.qhatu.feature.setting.data.database.dao.CategoryDao
import com.rgk.qhatu.feature.setting.data.database.dao.StoreDao
import com.rgk.qhatu.feature.setting.data.database.dao.UnitMeasureDao
import com.rgk.qhatu.feature.operation.data.database.dao.OperationDao
import com.rgk.qhatu.feature.operation.data.database.dao.OperationDetailDao
import com.rgk.qhatu.feature.purchase.data.database.dao.PurchaseDao
import com.rgk.qhatu.feature.sale.data.database.dao.SaleDao
import org.koin.dsl.module

val databaseModule = module {
    single {
        get<RoomDatabase.Builder<AppDataBase>>().build()
    }
    single<AuditLogDao> { get<AppDataBase>().auditLogDao() }
    single<BrandDao> { get<AppDataBase>().brandDao() }
    single<CategoryDao> { get<AppDataBase>().categoryDao() }
    single<CustomerDao> { get<AppDataBase>().clientDao() }
    single<PaymentDao> { get<AppDataBase>().clientPaymentDao() }
    single<ProductDao> { get<AppDataBase>().productDao() }
    single<UnitMeasureDao> { get<AppDataBase>().unitMeasureDao() }
    single<StoreDao> { get<AppDataBase>().storeDao() }
    single<ImageProductDao> { get<AppDataBase>().imageProductDao() }
    single<CartDao> { get<AppDataBase>().cartDao() }
    single<CartItemDao> { get<AppDataBase>().cartItemDao() }
    single<OperationDao> { get<AppDataBase>().operationDao() }
    single<OperationDetailDao> { get<AppDataBase>().operationDetailDao() }
    single<SaleDao> { get<AppDataBase>().saleDao() }
    single<PurchaseDao> { get<AppDataBase>().purchaseDao() }
}