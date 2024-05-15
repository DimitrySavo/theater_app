package ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

object UIState {
    const val PROFILE = "profile"
    const val POSTER = "poster"
    const val REPERTOIRE = "repertoire"

    var state = mutableStateOf(PROFILE)
        private set

    fun changeToProfile() {
        state.value = PROFILE
    }

    fun changeToPoster() {
        state.value = POSTER
    }

    fun changeToRepertoire() {
        state.value = REPERTOIRE
    }
}