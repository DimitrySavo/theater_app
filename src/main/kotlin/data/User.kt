package data

object UserData {
    data class User(
        var login: String? = null,
        var id: Int = 0,
        var firstName: String = "",
        var lastName: String = "",
        var email: String = "",
        var password: String = "",
        var mobilePhone: String = ""
    )

    val user: User = User()
}