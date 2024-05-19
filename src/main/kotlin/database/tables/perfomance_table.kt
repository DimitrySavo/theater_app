package database.tables

import database.DatabaseService.db
import database.tables.ClientsTable.primaryKey
import org.ktorm.dsl.*
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object PerfomanceTable: Table<Nothing>("theaterrepertoire") {
    val id = int("idperfomance").primaryKey()
    val genre = int("genrecode")
    val author = int("authorcode")
    val workInBase = varchar("workinbase")
    val perfomanceDuration = int("perfomanceduration")
    val ageRestrictions = varchar("agerestrictions")
    val amountOfIntermissions = int("amountofintermissions")
    val perfomanceName = varchar("perfomancename")
}

data class Perfomance(
    val id: Int,
    val genre: Int,
    val author: Int,
    val workInBase: String,
    val perfomanceDuration: Int,
    val ageRestrictions: String,
    val amountOfIntermissions: Int,
    val perfomanceName: String
)

object PerfomanceSupport{
    fun getPerfomance(id: Int): Perfomance {
        val row = db.from(PerfomanceTable).select().where { PerfomanceTable.id eq id }.map {
            Perfomance(
                id = it[PerfomanceTable.id] ?: 0,
                genre = it[PerfomanceTable.genre] ?: 0,
                author = it[PerfomanceTable.author] ?: 0,
                workInBase = it[PerfomanceTable.workInBase] ?: "",
                perfomanceDuration = it[PerfomanceTable.perfomanceDuration] ?: 0,
                ageRestrictions = it[PerfomanceTable.ageRestrictions] ?: "",
                amountOfIntermissions = it[PerfomanceTable.amountOfIntermissions] ?: 0,
                perfomanceName = it[PerfomanceTable.perfomanceName] ?: ""
            )
        }
        return if (row.isNotEmpty()) {
            row[0]
        } else {
            println("Error on id: $id")
            Perfomance(0, 0, 0, "", 0, "", 0, "")
        }
    }
}