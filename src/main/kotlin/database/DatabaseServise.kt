package database

import org.ktorm.database.Database

object DatabaseService {
    val db: Database = Database.connect(
        url = "jdbc:postgresql://localhost:5432/theater_db",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "zxc_ghoul")
}