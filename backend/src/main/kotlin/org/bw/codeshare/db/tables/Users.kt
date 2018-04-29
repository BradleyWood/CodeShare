package org.bw.codeshare.db.tables

import org.jetbrains.squash.definition.*

object Users : TableDefinition() {
    val id = integer("id").primaryKey().autoIncrement()
}
