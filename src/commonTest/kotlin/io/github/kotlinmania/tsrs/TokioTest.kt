// port-lint: tests ts-rs/src/tokio.rs
package io.github.kotlinmania.tsrs

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class TokioTest {
    private val simpleType =
        object : Ts {
            override fun name(): String = "User"

            override fun decl(): String = "type User = { id: number };"

            override fun inline(): String = "{ id: number }"
        }

    @Test
    fun testTokioPrimitives() {
        val mutex = TokioMutex(simpleType)
        val onceCell = TokioOnceCell(simpleType)
        val rwLock = TokioRwLock(simpleType)

        assertEquals("User", mutex.ident())
        assertEquals("User", mutex.name())
        assertEquals("{ id: number }", mutex.inline())
        assertEquals("type User = { id: number };", mutex.decl())
        assertFalse(mutex.isOption)

        assertEquals("User", onceCell.ident())
        assertEquals("User", rwLock.ident())
    }
}
