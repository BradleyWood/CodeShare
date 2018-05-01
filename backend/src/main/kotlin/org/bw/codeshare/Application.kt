package org.bw.codeshare

import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.locations.Locations
import org.jetbrains.ktor.routing.*

fun Application.main() {
    install(DefaultHeaders)
    install(Locations)

    routing {
        index()
        login()
    }
}

