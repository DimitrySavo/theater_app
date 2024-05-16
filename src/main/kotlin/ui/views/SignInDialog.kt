package ui.views

import ViewModel.ViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import data.model.User
import ui.theme.velvetRed

@Composable
fun SignInDialog(viewModel: ViewModel) {


    Dialog(onDismissRequest = {  }) {
        Card(
            modifier = Modifier
                .width(400.dp)
                .height(700.dp)
                .padding(vertical = 30.dp)
                .background(velvetRed),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column {
                outlined_text_field(placeholder = "Email", value = viewModel.userState.email,
                    onValueChange = { newValue -> viewModel.userState.email = newValue })
                outlined_text_field(placeholder = "Password", value = viewModel.userState.password,
                    onValueChange = { newValue -> viewModel.userState.password = newValue })
            }
        }
    }
}