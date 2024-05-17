package ui.views

import ViewModel.ViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ui.theme.halfTransparent
import ui.theme.velvetRed

@Composable
fun SignUpDialog(viewModel: ViewModel, title: String = "", onDismiss: () -> Unit) {


    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .padding(vertical = 30.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = title,
                    color = halfTransparent,
                    style = TextStyle(fontSize = 30.sp),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                outlined_text_field(placeholder = "Name", value = viewModel.userState.firstName,
                    onValueChange = { newValue -> viewModel.changeName(newValue) })

                outlined_text_field(placeholder = "Surname", value = viewModel.userState.lastName,
                    onValueChange = { newValue -> viewModel.changeSurname(newValue) })

                outlined_text_field(placeholder = "Patronymic", value = viewModel.userState.patronymic,
                    onValueChange = { newValue -> viewModel.changePatronymic(newValue) })

                outlined_text_field(placeholder = "Phone", value = viewModel.userState.mobilePhone,
                    onValueChange = { newValue -> viewModel.changePhone(newValue) })

                outlined_text_field(placeholder = "Login", value = viewModel.userState.email,
                    onValueChange = { newValue -> viewModel.changeEmail(newValue) })

                outlined_text_field(placeholder = "Password", value = viewModel.userState.password,
                    onValueChange = { newValue -> viewModel.changePassword(newValue) })

                DropdownMenu_text_field(listOf("Метод", "Метод 1", "Метод 2"))

                color_selected_button(
                    text = "Зарегистироваться",
                    onClick = { },
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    color = velvetRed,
                    currentIndex = 1,
                    icon = null
                )
            }
        }
    }
}