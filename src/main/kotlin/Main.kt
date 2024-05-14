

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import ui.theme.*
import ui.views.mainScreen


@Composable
@Preview
fun App() {
    mainScreen()
}

fun main() = application {
    val state = rememberWindowState(
        position = WindowPosition.Aligned(Alignment.Center),
        size = WindowSize(1200.dp, 800.dp)
    )
    Window(onCloseRequest = ::exitApplication, state = state) {
        App()
    }
}