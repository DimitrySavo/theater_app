package ui.views

import ViewModel.ViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import data.Benefits
import data.model.User
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
                modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text(
                    text = title,
                    color = halfTransparent,
                    style = TextStyle(fontSize = 30.sp),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                outlined_text_field(placeholder = "Name", value = viewModel.userState.firstName,
                    onValueChange = { newValue -> viewModel.changeName(newValue) }, checker =  User::checkNameSurname, errorText =  "Name must contain only letters")

                outlined_text_field(placeholder = "Surname", value = viewModel.userState.lastName,
                    onValueChange = { newValue -> viewModel.changeSurname(newValue) }, checker =  User::checkNameSurname, errorText =  "Surname must contain only letters")

                outlined_text_field(placeholder = "Patronymic", value = viewModel.userState.patronymic,
                    onValueChange = { newValue -> viewModel.changePatronymic(newValue) }, checker =  User::checkPatronymic, errorText =  "Patronymic must contain only letters")

                outlined_text_field(placeholder = "Phone", value = viewModel.userState.mobilePhone,
                    onValueChange = { newValue -> viewModel.changePhone(newValue) }, checker =  User::checkPhone, errorText =  "Phone must contain 11 digits")

                outlined_text_field(placeholder = "Login", value = viewModel.userState.email,
                    onValueChange = { newValue -> viewModel.changeEmail(newValue) }, checker =  User::checkEmail, errorText =  "Invalid email")

                outlined_text_field(placeholder = "Password", value = viewModel.userState.password,
                    onValueChange = { newValue -> viewModel.changePassword(newValue) }, checker =  User::checkPassword, errorText =  "Password must contain at least 8 characters")

                val benefitsList = Benefits.entries.map { it.nameBenefits }

                DropdownMenu_text_field(benefitsList) { newValue -> viewModel.changeBenefit(newValue) }

                color_selected_button(
                    text = "Зарегистироваться",
                    onClick = {
                        User.addUser(viewModel.userState)
                        onDismiss()
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