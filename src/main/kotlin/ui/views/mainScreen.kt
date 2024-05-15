package ui.views
import ViewModel.UIState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.theme.*

@Composable
fun mainScreen() {
    var currentUIState = UIState.state.value

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
            when (currentUIState) {
                UIState.PROFILE -> {
                    profileUnregister()
                }
                UIState.POSTER -> { Text("Poster") }
                UIState.REPERTOIRE -> {}
            }
        }
    }
}
