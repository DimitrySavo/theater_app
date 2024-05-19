package ui.views

import ViewModel.ViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import data.model.CurrentUser
import data.model.User
import ui.theme.black
import ui.theme.green
import ui.theme.halfTransparent
import ui.theme.velvetRed

@Composable
fun SignInDialog(viewModel: ViewModel, title: String = "", onDismiss: () -> Unit) {

    var isWrongPassword by remember { mutableStateOf(false) }

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

                outlined_text_field(placeholder = "Email", value = viewModel.userState.email,
                    onValueChange = { newValue -> viewModel.changeEmail(newValue) }, checker = User::checkExistEmail, errorText = "No such user with this email")
                Column(
                    modifier = Modifier.padding(horizontal = 40.dp, vertical = 0.dp).fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = viewModel.userState.password,
                        onValueChange = { newValue -> viewModel.changePassword(newValue) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = velvetRed,
                            cursorColor = velvetRed,
                        ),
                        label = {
                            Text("Пароль", color = black)
                        },
                        singleLine = true,
                    )
                    if (isWrongPassword) {
                        Text(
                            text = "Wrong password",
                            color = green,
                            modifier = Modifier.padding(top = 10.dp, start = 10.dp)
                        )
                    }
                }

                color_selected_button(
                    text = "Войти",
                    onClick = {
                        if(User.executeCheckUser(viewModel.userState.password, viewModel.userState.email)) {
                            isWrongPassword = false
                            User.fillUserAfterEnter(viewModel.userState.email, viewModel.userState.password)
                            viewModel.changeUser(CurrentUser.user)
                            onDismiss()
                        } else {
                            isWrongPassword = true
                        }
                    },
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