package ui.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier


@Composable
fun DropdownMenu_text_field(elements: List<String>) {
    var selectedText by remember {
        mutableStateOf("Метод")
    }

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Box {
        IconButton(onClick = { isExpanded = !isExpanded }) {
            Icon(Icons.Default.ThumbUp, contentDescription = "Localized description")
        }

        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            elements.forEach { text ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = text
                        isExpanded = false
                    }
                ) {
                    TextField(
                        value = selectedText,
                        onValueChange = {},
                        readOnly = true,
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
    DropdownMenu_text_field(listOf("Метод", "Метод 1", "Метод 2"))
}
