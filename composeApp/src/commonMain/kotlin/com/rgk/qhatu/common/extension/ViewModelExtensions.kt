package com.rgk.qhatu.common.extension

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
inline fun <reified T : ViewModel> getViewModelIfRouteMatches(
    currentRoute: String?,
    expectedRoute: String
): T? {
    return if (currentRoute == expectedRoute) {
        koinViewModel<T>()
    } else {
        null
    }
}

