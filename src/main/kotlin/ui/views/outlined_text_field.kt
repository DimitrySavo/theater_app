package ui.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun outlined_text_field(value: String ,onValueChange: (String) -> Unit, placeholder: String) {

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text(placeholder)},
        modifier = Modifier.padding(horizontal = 40.dp, vertical = 20.dp).fillMaxWidth(),
    )
}

@Preview
@Composable
fun preview() {
   // outlined_text_field(onValueChange = {}, "hello")
}