package org.bw.codeshare

import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.http.*
import org.jetbrains.ktor.locations.*
import org.jetbrains.ktor.routing.*

fun Route.login() {
    get<Login> {
        call.respond(HttpStatusCode.Forbidden)
    }
    post<Login> {
        var user = when {
            it.userName.toLowerCase() == "brad" -> ""
            else -> null
        }
    }
}
