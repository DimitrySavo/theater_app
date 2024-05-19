package data.model

import data.BenefitSupport
import data.Benefits
import data.Constants
import database.DatabaseService.db
import org.ktorm.dsl.*
import org.ktorm.entity.any
import org.ktorm.entity.maxBy
import org.ktorm.entity.sequenceOf
import kotlin.math.log


data class User(
    var id: Int = 0,
    var firstName: String = "",
    var lastName: String = "",
    var patronymic: String = "",
    var email: String = "",
    var password: String = "",
    var mobilePhone: String = "",
    var benefit: Benefits = Benefits.REGULAR

) {
    companion object {
        fun checkNameSurname(value: String): Boolean {
            return value.isNotEmpty() and value.matches(Constants.NAMES_REGEX)
        }

        fun checkPatronymic(value: String): Boolean {
            if (value.isEmpty())
                return true
            return value.matches(Constants.NAMES_REGEX)
        }

        fun checkPassword(value: String): Boolean {
            return value.length >= Constants.MIN_PASSWORD_LENGTH
        }

        fun checkPhone(value: String): Boolean {
            return value.length == Constants.PHONE_LENGTH
        }

        fun checkEmail(value: String): Boolean {
            return value.matches(Constants.EMAIL_REGEX)
        }

        private fun allChecks(userForCheck: User): Boolean {
            return checkNameSurname(userForCheck.firstName)
                    && checkNameSurname(userForCheck.lastName)
                    && checkPatronymic(userForCheck.patronymic)
                    && checkPhone(userForCheck.mobilePhone)
                    && checkEmail(userForCheck.email)
                    && checkPassword(userForCheck.password)
        }

        fun checksTicketBuyer(userForCheck: User): Boolean {
            return checkNameSurname(userForCheck.firstName)
                    && checkNameSurname(userForCheck.lastName)
                    && checkPhone(userForCheck.mobilePhone)
                    && checkEmail(userForCheck.email)
        }

        fun executeInsertAccount(clientid: Int, benefitCode: Int, login: String, password: String){
            db.useConnection { connection ->
                connection.prepareCall("CALL insert_user_account(?, ?, ?, ?)").use { statement ->
                    statement.setInt(1, clientid)
                    statement.setInt(2, benefitCode)
                    statement.setString(3, login)
                    statement.setString(4, password)
                    statement.execute()
                }
            }
        }

        fun checkExistEmail(email: String): Boolean {
            return db.from(database.tables.AccountTable).select(database.tables.AccountTable.login)
                .where(database.tables.AccountTable.login eq email)
                .map { it[database.tables.AccountTable.login] }.isNotEmpty()
        }

        fun checkNotExistEmail(email: String): Boolean {
            return db.from(database.tables.AccountTable).select(database.tables.AccountTable.login)
                .where(database.tables.AccountTable.login eq email)
                .map { it[database.tables.AccountTable.login] }.isEmpty()
        }

        fun checkExistPhone(phone: String): Boolean{
            return db.from(database.tables.ClientsTable).select(database.tables.ClientsTable.mobilePhone)
                .where(database.tables.ClientsTable.mobilePhone eq phone)
                .map { it[database.tables.ClientsTable.mobilePhone] }.isEmpty()
        }

        fun executeCheckUser(passwordentered: String, loginentered: String): Boolean {
            println("executeCheckUser called with loginentered: $loginentered, passwordentered: $passwordentered")
            if (checkEmail(loginentered)) {
                db.useConnection { connection ->
                    connection.prepareCall("select check_user(?, ?)").use { statement ->
                        statement.setString(1, passwordentered)
                        statement.setString(2, loginentered)
                        val result = statement.executeQuery()
                        if (result.next()) {
                            val resultBool = result.getBoolean(1)
                            println("Result boolean: $resultBool")
                            return resultBool
                        }
                    }
                }
            }
            return false
        }

        fun fillUserAfterEnter(login: String, password: String) {
            val newUser = User()

            println("Called with login: $login, password: $password")

            db.from(database.tables.ClientsTable).select()
                .where { database.tables.ClientsTable.email eq login }.forEach {
                    println("User from db: $it")
                    newUser.id = it[database.tables.ClientsTable.id]!!
                    newUser.firstName = it[database.tables.ClientsTable.firstName]!!
                    newUser.lastName = it[database.tables.ClientsTable.lastName]!!
                    newUser.patronymic = it[database.tables.ClientsTable.patronymic]!!
                    newUser.email = it[database.tables.ClientsTable.email]!!
                    newUser.mobilePhone = it[database.tables.ClientsTable.mobilePhone]!!
                    newUser.benefit = BenefitSupport.getBenefit(it[database.tables.ClientsTable.benefit]!!)
                }

            CurrentUser.changeUser(newUser)

        }

        fun addUser(newUser: User, buyer: Boolean = false): Boolean {
            val isClientBefore =
                db.sequenceOf(database.tables.ClientsTable).any { it.email eq newUser.email }

            if (isClientBefore) {
                val clientId = db.from(database.tables.ClientsTable).select(database.tables.ClientsTable.id)
                    .where { database.tables.ClientsTable.email eq newUser.email }
                    .map { it[database.tables.ClientsTable.id] }.first()!!

                //TODO add check for email of existing user and maybe handle it. Or just let it be

                executeInsertAccount(clientId, newUser.benefit.id, newUser.email, newUser.password)

                CurrentUser.changeUser(newUser)

                return true
            } else {
                if(buyer){
                    if (checksTicketBuyer(newUser)) {
                        db.insert(database.tables.ClientsTable) {
                            set(it.firstName, newUser.firstName)
                            set(it.lastName, newUser.lastName)
                            set(it.patronymic, newUser.patronymic)
                            set(it.email, newUser.email)
                            set(it.mobilePhone, newUser.mobilePhone)
                            set(it.benefit, newUser.benefit.id)
                        }

                        val lastClient = db.from(database.tables.ClientsTable).select(database.tables.ClientsTable.id)
                            .where { database.tables.ClientsTable.mobilePhone eq newUser.mobilePhone }
                            .map { it[database.tables.ClientsTable.id] }.first()!!

                        executeInsertAccount(lastClient, newUser.benefit.id, newUser.email, newUser.password)

                        newUser.id = lastClient

                        CurrentUser.changeUser(newUser)

                        return true
                    } else {
                        println("User not added")
                        return false
                    }
                }
                if (allChecks(newUser)) {
                    db.insert(database.tables.ClientsTable) {
                        set(it.firstName, newUser.firstName)
                        set(it.lastName, newUser.lastName)
                        set(it.patronymic, newUser.patronymic)
                        set(it.email, newUser.email)
                        set(it.mobilePhone, newUser.mobilePhone)
                        set(it.benefit, newUser.benefit.id)
                    }

                    val lastClient = db.from(database.tables.ClientsTable).select(database.tables.ClientsTable.id)
                        .where { database.tables.ClientsTable.mobilePhone eq newUser.mobilePhone }
                        .map { it[database.tables.ClientsTable.id] }.first()!!

                    executeInsertAccount(lastClient, newUser.benefit.id, newUser.email, newUser.password)

                    newUser.id = lastClient

                    CurrentUser.changeUser(newUser)

                    return true
                } else {
                    println("User not added")
                    return false
                }
            }
        }
    }
}

    object CurrentUser {
        var user = User()
            private set

        fun changeUser(newUser: User) {
            user = newUser
        }
    }