package org.bw.codeshare

import kotlinx.html.*
import org.jetbrains.ktor.html.*

class ApplicationPage : Template<HTML> {
    val pageTitle = Placeholder<TITLE>()
    val head = Placeholder<HEAD>()

    override fun HTML.apply() {
        head {
            meta { charset = "utf-8" }
            meta {
                name = "viewport"
                content = "width=device-width, initial-scale=1.0"
            }
            title {
                insert(pageTitle)
            }
            insert(head)
        }
        body {
            div { id = "content" }
            script(src = "frontend/frontend.bundle.js")
        }
    }
}
