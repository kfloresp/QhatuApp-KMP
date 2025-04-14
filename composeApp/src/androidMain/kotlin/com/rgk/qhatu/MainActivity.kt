package com.rgk.qhatu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rgk.qhatu.data.database.Factory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = Factory(application).createRoomDatabase()
            App(db.configurationDao())
        }
    }
}
