package ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import data.model.User

class ViewModel {
    var userState by mutableStateOf(User())
        private set

    fun changeEmail(newEmail: String) {
        userState = userState.copy(email = newEmail)
    }

    fun changePassword(newPassword: String) {
        userState = userState.copy(password = newPassword)
    }

    fun changeName(newValue: String) {
        userState = userState.copy(firstName = newValue)
    }

    fun changeSurname(newValue: String) {
        userState = userState.copy(lastName = newValue)
    }

    fun changePatronymic(newValue: String) {
        userState = userState.copy(patronymic = newValue)
    }

    fun changePhone(newValue: String) {
        userState = userState.copy(mobilePhone = newValue)
    }

}