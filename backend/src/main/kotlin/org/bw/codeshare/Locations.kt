package org.bw.codeshare

import org.jetbrains.ktor.locations.location

@location("/")
class Index

@location("/login")
data class Login(val userName: String, val password: String)
