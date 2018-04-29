package org.bw.codeshare.db.tables

import org.jetbrains.squash.definition.*

object Users : TableDefinition() {
    val id = integer("id").primaryKey().autoIncrement()
    val email = varchar("email", 254).uniqueIndex()
    val userName = varchar("userName", 24)
    val passwordHash = varchar("passwordHash", 64)
    val creationDate = long("creationDate")
    val lastLogin = long("lastLogin")
}
