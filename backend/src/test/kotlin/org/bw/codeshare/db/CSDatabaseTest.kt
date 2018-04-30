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

    @Test
    fun login() {
        val user = User(0, "abc@def.com", "hi", "password")
        db.createUser(user)
        assertEquals(user, db.getUser("abc@def.com", "password"))
        assertEquals(user, db.getUser("Abc@def.com", "password"))

        assertNull(db.getUser("abc@def.com", "password1"))
        assertNull(db.getUser("abc@def.com1", "password"))
    }
}
