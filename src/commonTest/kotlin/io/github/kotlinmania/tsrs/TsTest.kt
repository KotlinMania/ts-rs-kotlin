// port-lint: tests ts-rs/src/lib.rs
package io.github.kotlinmania.tsrs

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class TsTest {
    private val simpleType =
        object : Ts {
            override fun name(): String = "User"

            override fun decl(): String = "type User = { id: number, name: string };"

            override fun inline(): String = "{ id: number, name: string }"
        }

    private val genericType =
        object : Ts {
            override fun name(): String = "Container<T>"

            override fun decl(): String = "type Container<T> = { value: T };"

            override fun inline(): String = "{ value: T }"
        }

    @Test
    fun testIdent() {
        assertEquals("User", simpleType.ident())
        assertEquals("Container", genericType.ident())
    }

    @Test
    fun testDefaults() {
        assertNull(simpleType.docs())
        assertNull(simpleType.outputPath())
        assertEquals(emptyList(), simpleType.dependencies())
        assertEquals("type User = { id: number, name: string };", simpleType.declConcrete())
        assertFailsWith<IllegalStateException> {
            simpleType.inlineFlattened()
        }
    }

    @Test
    fun testDummy() {
        assertEquals("Dummy", Dummy.fmt())
        assertEquals("Dummy", Dummy.toString())
        assertEquals("Dummy", Dummy.name())
        assertEquals("", Dummy.inline())
        assertFailsWith<IllegalStateException> {
            Dummy.decl()
        }
        assertFailsWith<IllegalStateException> {
            Dummy.declConcrete()
        }
        assertFailsWith<IllegalStateException> {
            Dummy.inlineFlattened()
        }
    }

    @Test
    fun testVisit() {
        val list = mutableListOf<Dependency>()
        val visit = Visit(list)
        visit.visit(simpleType)
        assertEquals(0, list.size)
    }
}
