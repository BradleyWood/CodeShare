import react.dom.*

import kotlin.browser.*

external fun require(res: String): dynamic

fun main(args: Array<String>) {
    require("style.css")

    render(document.getElementById("content")) {
        div {

        }
    }

}
