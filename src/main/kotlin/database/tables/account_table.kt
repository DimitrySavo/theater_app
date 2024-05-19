package database.tables

import database.tables.ClientsTable.primaryKey
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object AccountTable: Table<Nothing>("clients_accounts") {
    val login = varchar("login").primaryKey()
    val password = varchar("password")
    val client_code = int("client_code")
}