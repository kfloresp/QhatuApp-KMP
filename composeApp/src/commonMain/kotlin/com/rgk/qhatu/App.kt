package com.rgk.qhatu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.rgk.qhatu.navigation.AppNavGraph
import com.rgk.qhatu.common.theme.QhatuTheme
import com.rgk.qhatu.presentation.MainViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    QhatuTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            AppNavGraph()
        }
    }
}