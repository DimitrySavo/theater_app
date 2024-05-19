package ui.views

import ViewModel.ViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import data.model.CurrentUser

@Composable
fun ProfileRegistered(viewModel: ViewModel) {
    var user by remember {
        mutableStateOf(CurrentUser.user)
    }
    Column {
        Text(text = "Здравствуйте, ${user.lastName} ${user.lastName}!")
        Text(text = "Ваша почта: ${user.email}")
        Text(text = "Ваш телефон: ${user.mobilePhone}")
        Text(text = "Ваша льгота: ${user.benefit.nameBenefits}")
    }
}