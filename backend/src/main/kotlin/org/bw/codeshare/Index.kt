package org.bw.codeshare

import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.html.respondHtmlTemplate
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.locations.get
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.contentType

fun Route.index() {
    contentType(ContentType.Text.Html) {
        get<Index> {
            call.respondHtmlTemplate(ApplicationPage()) {
                pageTitle { +"Welcome" }
            }
        }
    }
    contentType(ContentType.Application.Json) {

    }
}
