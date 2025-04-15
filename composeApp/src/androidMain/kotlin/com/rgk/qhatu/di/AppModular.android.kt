package com.rgk.qhatu.di

import com.rgk.qhatu.data.database.getDatabaseBuilder
import org.koin.dsl.module

actual val nativeModule = module {
    single {
        getDatabaseBuilder(get())
    }
}