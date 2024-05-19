package data

enum class Benefits(val nameBenefits: String = "",val id: Int = 6, val discount: Double) {
    STUDENT("Студент", 1, 0.1),
    PENSIONER("Пенсионер",2, 0.25),
    VETERAN("Ветеран", 5, 0.3),
    DISABLED("Инвалид", 4, 0.5),
    KID("Ребенок",3, 0.8),
    REGULAR("Без категории", 6, 0.0)

}

object BenefitSupport{
    fun getBenefit(code: Int): Benefits{
        return when(code){
            1 -> Benefits.STUDENT
            2 -> Benefits.PENSIONER
            3 -> Benefits.KID
            4 -> Benefits.DISABLED
            5 -> Benefits.VETERAN
            else -> Benefits.REGULAR
        }
    }
}