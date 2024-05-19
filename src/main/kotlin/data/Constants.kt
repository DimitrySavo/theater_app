package data

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

object Constants {
    const val MAX_TEXT_FIELD_LENGTH = 50
    const val PHONE_LENGTH = 11
    const val MIN_PASSWORD_LENGTH = 8
    val NAMES_REGEX = Regex("^[а-яА-Я-]*$")
    val EMAIL_REGEX: Regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$")
    val DATE_FORMAT = DateTimeFormatter.ofPattern("HH:mm dd-MM yy")

}