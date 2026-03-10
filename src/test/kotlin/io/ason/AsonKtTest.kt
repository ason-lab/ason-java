package io.ason

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.assertFalse

data class KUser(
    var id: Long = 0,
    var name: String? = null,
    var active: Boolean = false
)

class AsonKtTest {

    @Test
    fun testDecodeSingle() {
        val input = "{id,name,active}:(42,Alice,true)"
        val user: KUser = decode(input)
        
        assertEquals(42L, user.id)
        assertEquals("Alice", user.name)
        assertTrue(user.active)
    }

    @Test
    fun testDecodeSingleNullable() {
        // Omitting the second value parses as null/empty
        val input = "{id,name,active}:(42,,false)"
        val user: KUser = decode(input)
        
        assertEquals(42L, user.id)
        assertNull(user.name)
        assertFalse(user.active)
    }

    @Test
    fun testDecodeList() {
        val input = "[{id,name,active}]:(1,Alice,true),(2,Bob,false)"
        val users: List<KUser> = decodeList(input)
        
        assertEquals(2, users.size)
        // Check first record
        assertEquals(1L, users[0].id)
        assertEquals("Alice", users[0].name)
        assertTrue(users[0].active)
        // Check second record
        assertEquals(2L, users[1].id)
        assertEquals("Bob", users[1].name)
        assertFalse(users[1].active)
    }

    @Test
    fun testDecodeByteArray() {
        val bytes = "{id,name,active}:(99,Carol,true)".toByteArray()
        val user: KUser = decode(bytes)
        
        assertEquals(99L, user.id)
        assertEquals("Carol", user.name)
        assertTrue(user.active)
    }
}
