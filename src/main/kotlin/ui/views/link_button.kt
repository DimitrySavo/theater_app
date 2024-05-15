package ui.views

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import org.jetbrains.skiko.Cursor
import ui.theme.blue

@Composable
fun link_button(onClick: () -> Unit, text: String, modifier: Modifier) {
    ClickableText(
        text = AnnotatedString(
            text = text,
            spanStyle = SpanStyle(fontSize = 20.sp, textDecoration = TextDecoration.Underline),
        ),
        onClick = {
            onClick()
        },
        style = TextStyle(color = blue),
        modifier = Modifier.then(modifier)
    )
}