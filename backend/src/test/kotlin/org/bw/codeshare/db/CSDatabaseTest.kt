package org.bw.codeshare.db

import org.bw.codeshare.Privacy
import org.bw.codeshare.Snippet
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

        try {
            db.createUser(user)
            fail("User already exists")
        } catch (e: Exception) {
        }
    }

    @Test
    fun createSnippet() {
        val user = User(5, "abc@def.com", "hi", "password")
        db.createUser(user)
        // for referential constraints

        val snippet = Snippet(0, user.id, 0, 0, Privacy.PUBLIC, Privacy.OWNER, "Title", "Data")
        db.createSnippet(snippet)

        try {
            db.createSnippet(snippet)
            fail("Snippet already exists")
        } catch (e: Exception) {
        }
    }
}
