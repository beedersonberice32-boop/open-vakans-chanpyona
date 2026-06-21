package com.vakans.chanpyona.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import com.vakans.chanpyona.presentation.screens.HomeScreen
import com.vakans.chanpyona.presentation.theme.OpenVakansChanpyonaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenVakansChanpyonaTheme {
                Surface(
                    color = Color(0xFF0a0e27)
                ) {
                    HomeScreen()
                }
            }
        }
    }
}
