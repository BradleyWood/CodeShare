package org.bw.codeshare.db

import org.bw.codeshare.Snippet
import org.bw.codeshare.User
import org.bw.codeshare.db.tables.Snippets
import org.bw.codeshare.db.tables.Snippets.creationDate
import org.bw.codeshare.db.tables.Snippets.editRights
import org.bw.codeshare.db.tables.Snippets.expiryDate
import org.bw.codeshare.db.tables.Snippets.owner
import org.bw.codeshare.db.tables.Snippets.readRights
import org.bw.codeshare.db.tables.Snippets.snippet
import org.bw.codeshare.db.tables.Snippets.title
import org.bw.codeshare.db.tables.Users
import org.jetbrains.squash.connection.BinaryObject
import org.jetbrains.squash.connection.DatabaseConnection
import org.jetbrains.squash.connection.transaction
import org.jetbrains.squash.dialects.h2.H2Connection
import org.jetbrains.squash.expressions.eq
import org.jetbrains.squash.query.from
import org.jetbrains.squash.query.where
import org.jetbrains.squash.results.get
import org.jetbrains.squash.schema.create
import org.jetbrains.squash.statements.deleteFrom
import org.jetbrains.squash.statements.insertInto
import org.jetbrains.squash.statements.values

class CSDatabase(val db: DatabaseConnection = H2Connection.createMemoryConnection()) {

    init {
        db.transaction {
            databaseSchema().create(Users, Snippets)
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

    fun createSnippet(new_snippet_: Snippet) = db.transaction {
        insertInto(Snippets).values {
            it[id] = new_snippet_.id
            it[owner] = new_snippet_.owner
            it[creationDate] = System.currentTimeMillis()
            it[expiryDate] = new_snippet_.expiryDate
            it[readRights] = new_snippet_.readRights
            it[editRights] = new_snippet_.editRights
            it[title] = new_snippet_.title
            it[snippet] = BinaryObject.fromByteArray(this@transaction, new_snippet_.snippet.toByteArray())
        }.execute()
    }

    fun findSnippetById(id: Int): Snippet? {
        var snippet = db.transaction {
            from(Snippets).where(Snippets.id eq id).execute().map {
                Snippet(id, it[owner], it[creationDate], it[expiryDate]
                        ?: -1, it[readRights], it[editRights], it[title], String(it[snippet].bytes))
            }.singleOrNull()
        }

        if (snippet!!.expiryDate > 0 && System.currentTimeMillis() > snippet.expiryDate)
            deleteSnippet(snippet.id)

        return snippet
    }

    fun deleteSnippet(id: Int) = db.transaction {
        deleteFrom(Users).where(Snippets.id eq id).execute()
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
