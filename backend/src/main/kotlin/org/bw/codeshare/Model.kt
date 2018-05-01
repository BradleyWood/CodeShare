package org.bw.codeshare

data class User(val id: Int, val email: String, val userName: String, val passwordHash: String)

data class Snippet
(
        val id: Int,
        val owner: Int,
        val creationDate: Long,
        val expiryDate: Long,
        val readRights: Privacy,
        val editRights: Privacy,
        val title: String,
        val snippet: String
)

enum class Privacy {
    PUBLIC,
    ORG,
    SPECIFIC_USERS,
    OWNER
}