package data.model

import data.Benefits
import data.SeatsType
import data.TicketTypes
import database.DatabaseService.db

data class Ticket(
    val initialCost: Double,
    val seatPlace: Int,
    val seatType: SeatsType,
    val ticket: TicketTypes
) {
    fun calculatePrice(clientCategory: Benefits): Double {
        return initialCost + initialCost * seatType.additionToCost + initialCost * ticket.additionToCost + initialCost * clientCategory.discount
    }
}

object TicketSupport{
    fun buyTicket(seatTypeCode: Int, ticketCategoryCode: Int, clientCode: Int, performanceOnPosterCode: Int, seatNumber: Int) {
        db.useConnection { connection ->
            val callableStatement = connection.prepareCall("CALL buy_ticket(?, ?, ?, ?, ?)")
            callableStatement.setInt(1, seatTypeCode)
            callableStatement.setInt(2, ticketCategoryCode)
            callableStatement.setInt(3, clientCode)
            callableStatement.setInt(4, performanceOnPosterCode)
            callableStatement.setInt(5, seatNumber)

            callableStatement.execute()
        }
    }
}