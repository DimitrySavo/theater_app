package data

enum class SeatsType(val typeName: String, val additionToCost: Double, val id: Int) {
    VIPLODGE("VIP-лоджия", 0.3, 3),
    LODGE("Лоджия", 0.15, 1),
    REGULAR("Партер", 0.0, 2);
}

object SeatsTypeSupport {
    fun getSeatType(name: String): SeatsType {
        return when (name) {
            SeatsType.LODGE.typeName -> SeatsType.LODGE
            SeatsType.REGULAR.typeName -> SeatsType.REGULAR
            else -> SeatsType.VIPLODGE
        }
    }
}