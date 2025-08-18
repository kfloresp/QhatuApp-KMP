package com.rgk.qhatu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rgk.qhatu.shared.SharedImageStorage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedImageStorage.init(this)
        setContent {
            App()
        }
    }
}
