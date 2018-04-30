package org.bw.codeshare.db

import org.bw.codeshare.User
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class CSDatabaseTest {

    lateinit var db: CSDatabase

    @Before
    fun setup() {
        db = CSDatabase()
    }

    @Test
    fun createUser() {
        val user = User(0, "bradley.wood@uoit.net", "brad", "password")
        db.createUser(user)
        assertEquals(user, db.findByEmail("bradley.wood@uoit.net"))
    }
}
