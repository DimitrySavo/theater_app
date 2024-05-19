package ui.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.theme.velvetRed


@Composable
fun DropdownMenu_text_field(elements: List<String>, onValuesChanged: (String) -> Unit) {
    var selectedText by remember {
        mutableStateOf("Выберите льготу")
    }

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Box {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {
                selectedText = it
            },
            label = { Text("Льгота") },
            trailingIcon = {
                IconButton(onClick = { isExpanded = true }) {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Localized description")
                }
            },
            modifier = Modifier
                .padding(horizontal = 40.dp, vertical = 0.dp)
                .fillMaxWidth(),
            readOnly = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = velvetRed,
                cursorColor = velvetRed
            ),
        )

        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            elements.forEach { text ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = text
                        onValuesChanged(selectedText)
                        isExpanded = false
                    }
                ) {
                    Text(
                        text = text,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}



@Preview
@Composable
fun previewDropdownMenu() {
    //DropdownMenu_text_field(listOf("Метод", "Метод 1", "Метод 2"))
}
