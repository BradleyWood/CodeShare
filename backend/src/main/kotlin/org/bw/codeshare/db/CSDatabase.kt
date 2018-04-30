package org.bw.codeshare.db

import org.bw.codeshare.User
import org.bw.codeshare.db.tables.Users
import org.jetbrains.squash.connection.DatabaseConnection
import org.jetbrains.squash.connection.transaction
import org.jetbrains.squash.dialects.h2.H2Connection
import org.jetbrains.squash.expressions.eq
import org.jetbrains.squash.query.from
import org.jetbrains.squash.query.where
import org.jetbrains.squash.results.get
import org.jetbrains.squash.schema.create
import org.jetbrains.squash.statements.insertInto
import org.jetbrains.squash.statements.values

class CSDatabase(val db: DatabaseConnection = H2Connection.createMemoryConnection()) {

    init {
        db.transaction {
            databaseSchema().create(Users)
        }
    }

    fun createUser(user: User) = db.transaction {
        insertInto(Users).values {
            it[id] = user.id
            it[email] = user.email.toLowerCase()
            it[userName] = user.userName
            it[passwordHash] = user.passwordHash
            it[creationDate] = System.currentTimeMillis()
            it[lastLogin] = System.currentTimeMillis()
        }.execute()
    }

    fun findByEmail(email: String) = db.transaction {
        from(Users).where(Users.email eq email.toLowerCase()).execute()
                .map {
                    User(it[Users.id], email.toLowerCase(), it[Users.userName], it[Users.passwordHash])
                }.singleOrNull()
    }

    fun getUser(email: String, passwordHash: String) = db.transaction {
        from(Users).where {
            Users.email eq email.toLowerCase()
        }.where {
            Users.passwordHash eq passwordHash
        }.execute().map {
            User(it[Users.id], email.toLowerCase(), it[Users.userName], it[Users.passwordHash])
        }.singleOrNull()
    }
}
