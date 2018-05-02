package org.bw.codeshare

import org.bw.codeshare.db.CSDatabase
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.http.*
import org.jetbrains.ktor.locations.*
import org.jetbrains.ktor.routing.*

fun Route.login(db: CSDatabase) {
    get<Login> {
        call.respond(HttpStatusCode.Forbidden)
    }
    post<Login> {
        val user = when {
            it.userName.length <= 4 -> null
            it.password.length <= 6 -> null
            else -> db.getUser(it.userName, it.password) // hash later
        }
        if (user != null) {
            call.respond("Welcome ${user.userName}")
        } else {
            call.respond("Invalid login information")
        }
    }
}
