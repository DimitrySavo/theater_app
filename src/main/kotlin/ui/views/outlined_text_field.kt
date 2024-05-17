package ui.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ui.theme.black
import ui.theme.green
import ui.theme.velvetRed

@Preview
@Composable
fun outlined_text_field(value: String ,onValueChange: (String) -> Unit, placeholder: String) {
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier
            .padding(horizontal = 40.dp, vertical = 0.dp)
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = velvetRed,
            cursorColor = velvetRed
        ),
        label = {
                Text(placeholder, color = black)
        }
    )
}

@Preview
@Composable
fun preview() {
   // outlined_text_field(onValueChange = {}, "hello")
}