package com.rgk.qhatu

import androidx.compose.ui.window.ComposeUIViewController
import com.rgk.qhatu.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    App()
}