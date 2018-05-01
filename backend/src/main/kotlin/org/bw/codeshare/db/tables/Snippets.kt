package org.bw.codeshare.db.tables

import org.bw.codeshare.Privacy
import org.jetbrains.squash.definition.*

object Snippets : TableDefinition() {
    val id = integer("id").primaryKey().autoIncrement()
    val owner = reference(Users.id, "owner")
    val creationDate = long("creation_date")
    val expiryDate = long("expiry_date").nullable()
    val readRights = privacy("read_rights")
    val editRights = privacy("edit_rights")
    val title = varchar("title", 256)
    val snippet = blob("snippet")
}

fun TableDefinition.privacy(name: String): ColumnDefinition<Privacy> {
    return createColumn(name, EnumColumnType(Privacy::class))
}
