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

}