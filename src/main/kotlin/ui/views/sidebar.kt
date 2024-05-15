package ui.views

import ViewModel.UIState
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.DragData
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.loadXmlImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import ui.theme.*

@Composable
@Preview
fun sidebar() {
    var indexPicked by remember {
        mutableStateOf(1)
    }

    val chosenColor by animateColorAsState(
        targetValue = velvetRed,
        animationSpec = tween(durationMillis = 3000, easing = LinearOutSlowInEasing)
    )

    Box(
        modifier = Modifier.fillMaxSize(),
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            color_selected_button(modifier = Modifier, index = 1,
                color = chosenColor, onClick = { indexPicked = 1; UIState.changeToProfile()}, indexPicked, Icons.Default.Person, text = "Profile")


            Spacer(Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 24.dp, start = 10.dp, end = 10.dp).height(1.dp).background(halfTransparent))

            color_selected_button(modifier = Modifier, index = 2,
                color = chosenColor, onClick = { indexPicked = 2; UIState.changeToPoster() }, indexPicked, Icons.Default.Menu, text = "Poster")

            color_selected_button(modifier = Modifier, index = 3,
                color = chosenColor, onClick = { indexPicked = 3; UIState.changeToRepertoire() }, indexPicked, Icons.Default.Settings, text = "Repertoire")
        }
    }
}