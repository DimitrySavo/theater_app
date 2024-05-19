package database.tables

import database.DatabaseService.db
import database.tables.PerfomanceTable.primaryKey
import org.ktorm.dsl.*
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object GenreTable: Table<Nothing>("genres") {
    val id = int("idgenre").primaryKey()
    val genreName = varchar("genrename")
    val genreDescription = varchar("genredescription")
}

object GenreSupport {
    fun getGenreName(id: Int): String{
        return db.from(GenreTable).select(GenreTable.genreName).where { GenreTable.id eq id }.map { it[GenreTable.genreName] }.first()!!
    }
}