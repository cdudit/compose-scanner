package fr.cdudit.composescanner.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = LightBlue,
    secondary = LightPurple,
    background = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
)


@Composable
fun ComposeScannerTheme(content: @Composable () -> Unit) {
    val colors = LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}