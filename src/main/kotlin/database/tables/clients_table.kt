package database.tables

import org.ktorm.schema.*

object ClientsTable: Table<Nothing>("clients") {
    val id = int("idclients").primaryKey()
    val firstName = varchar("clientname")
    val lastName = varchar("clientsurname")
    val patronymic = varchar("clinetpatronymic")
    val email = varchar("clientemail")
    val mobilePhone = varchar("clientphone")
    val benefit = int("benefitcode")
}