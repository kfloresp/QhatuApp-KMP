package com.rgk.qhatu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.rgk.qhatu.data.database.dao.ConfigurationDao
import com.rgk.qhatu.ui.navigation.AppNavGraph
import com.rgk.qhatu.utils.QhatuTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(configurationDao: ConfigurationDao) {
    QhatuTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            AppNavGraph()
        }
    }
}
