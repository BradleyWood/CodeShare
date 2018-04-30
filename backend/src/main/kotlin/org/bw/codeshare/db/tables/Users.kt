package org.bw.codeshare.db.tables

import org.jetbrains.squash.definition.*

object Users : TableDefinition() {
    val id = integer("id").primaryKey().autoIncrement()
    val email = varchar("email", 254).uniqueIndex()
    val userName = varchar("username", 24)
    val passwordHash = varchar("password_hash", 64)
    val creationDate = long("creation_date")
    val lastLogin = long("last_login")
}
