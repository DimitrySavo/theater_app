package data

enum class Benefits(name: String = "") {
    STUDENT("Студент"),
    PENSIONER,
    VETERAN,
    DISABLED,
    REGULAR("Без категории")
}