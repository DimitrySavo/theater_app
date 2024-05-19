package database.tables

import database.DatabaseService.db
import database.tables.GenreTable.primaryKey
import org.ktorm.schema.*
import java.sql.Timestamp
import java.sql.Types
import java.time.LocalDateTime

object PosterTable: Table<Nothing>("theater_poster") {
    val id = int("idperfomance_on_poster").primaryKey()
    val perfomanceCode = int("perfomancecode")
    val hallCode = int("hallcode")
    val perfomanceDateTime = timestamp("perfomancedatetime")
    val perfomanceInitialTicketCost = double("inintialticketcost")
}

data class PerfomanceOnPoster(
    val id: Int,
    val perfomanceCode: Int,
    val hallCode: Int,
    val perfomanceDateTime: String,
    val perfomanceInitialTicketCost: Double
)

object PosterSupport{
    fun executeGetTotalFreeSeats(id: Int): Int{
        var freeSeats = 0

        db.useConnection { connection ->
            val statement = connection.prepareCall("{? = call get_all_free_seats(?)}")
            statement.registerOutParameter(1, Types.INTEGER)
            statement.setInt(2, id)
            statement.execute()
            freeSeats = statement.getInt(1)
        }

        println("Free seats: $freeSeats")

        return freeSeats
    }
}