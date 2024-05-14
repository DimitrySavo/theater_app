package ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ui.theme.black
import ui.theme.transparent
import ui.theme.white

@Composable
fun color_selected_button(
    modifier: Modifier = Modifier,
    index: Int = 1,
    color: Color,
    onClick: () -> Unit,
    currentIndex: Int,
    icon: ImageVector,
    text: String) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().then(modifier),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if(currentIndex == index) color else transparent,
            contentColor = if(currentIndex == index) white else black
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = if(currentIndex == index) 8.dp else 0.dp,
            hoveredElevation = 0.dp),
    ){
        Box() {
            Icon(icon, "", modifier = Modifier.padding(start = 5.dp))
            Text(text = text, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }
    }
}