package data.model

import data.Benefits


data class User(
    var login: String? = null,
    var id: Int = 0,
    var firstName: String = "",
    var lastName: String = "",
    var patronymic: String = "",
    var email: String = "",
    var password: String = "",
    var mobilePhone: String = "",
    var benefit: Benefits = Benefits.REGULAR
)
