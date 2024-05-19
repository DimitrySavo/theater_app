package data

enum class TicketTypes(val typeName: String, val additionToCost: Double, val id: Int) {
    DEFAULT("Обычный", 0.05, 4),
    ECONOMY("Эконом", 0.0, 2),
    PREMIUM("Премиум", 0.1, 1),
    VIP("VIP", 0.25, 3)
}

object TicketTypesSupport {
    fun getTicketType(name: String): TicketTypes {
        return when (name) {
            TicketTypes.ECONOMY.typeName -> TicketTypes.ECONOMY
            TicketTypes.PREMIUM.typeName -> TicketTypes.PREMIUM
            TicketTypes.VIP.typeName -> TicketTypes.VIP
            else -> TicketTypes.DEFAULT
        }
    }
}