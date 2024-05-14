package ui.views
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.theme.*

@Composable
fun mainScreen() {
    Row(modifier = Modifier.fillMaxSize().background(white)) {
        Column(
            modifier = Modifier.fillMaxHeight().background(white).padding(16.dp).weight(1f),
        ) {
            sidebar()
        }

        Spacer(Modifier.fillMaxHeight().padding(start = 10.dp, end = 10.dp).width(1.dp).background(halfTransparent))


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight().weight(2f).background(white).padding(16.dp)
        ) {
            Text(text = "Bye World")
        }
    }
}