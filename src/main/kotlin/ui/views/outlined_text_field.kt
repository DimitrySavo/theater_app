package ui.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import data.Constants
import ui.theme.black
import ui.theme.green
import ui.theme.velvetRed

@Preview
@Composable
fun outlined_text_field(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    checker: (String) -> Boolean,
    errorText: String,
    isReadOnly:  Boolean = false
) {
    var isError by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(horizontal = 40.dp, vertical = 0.dp).fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                isError = !checker(it)
                if (it.length <= Constants.MAX_TEXT_FIELD_LENGTH)
                    onValueChange(it)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = velvetRed,
                cursorColor = velvetRed,
            ),
            label = {
                Text(placeholder, color = black)
            },
            singleLine = true,
            readOnly = isReadOnly
        )
        if (isError) {
            Text(text = errorText, color = green)
        }
    }
}

@Preview
@Composable
fun preview() {
    // outlined_text_field(onValueChange = {}, "hello")
}