package com.rgk.qhatu

import androidx.compose.ui.window.ComposeUIViewController
import com.rgk.qhatu.data.database.Factory

fun MainViewController() = ComposeUIViewController {
    val database = Factory().createRoomDatabase()
    App(database.configurationDao())
}