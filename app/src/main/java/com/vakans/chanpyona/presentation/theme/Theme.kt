package com.vakans.chanpyona.presentation.theme

import androidx.compose.foundation.isSystemInDarkMode
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Custom Colors
val PrimaryDark = Color(0xFF1a1a2e)
val PrimaryLight = Color(0xFF16213e)
val Secondary = Color(0xFF0f3460)
val Tertiary = Color(0xFF4ECDC4)
val Accent = Color(0xFFFF6B6B)
val Background = Color(0xFF0a0e27)
val Surface = Color(0xFF161d31)
val OnBackground = Color.White
val OnSurface = Color(0xFFB0B0B0)

private val DarkColorScheme = darkColorScheme(
    primary = Accent,
    secondary = Tertiary,
    tertiary = Secondary,
    background = Background,
    surface = Surface,
    onBackground = OnBackground,
    onSurface = OnSurface,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = Accent,
    secondary = Tertiary,
    tertiary = Secondary,
    background = Color.White,
    surface = Color(0xFFF5F5F5),
    onBackground = Color.Black,
    onSurface = Color(0xFF666666)
)

@Composable
fun OpenVakansChanpyonaTheme(
    darkTheme: Boolean = isSystemInDarkMode(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
