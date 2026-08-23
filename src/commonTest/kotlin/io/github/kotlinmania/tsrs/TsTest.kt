// port-lint: tests lib.rs
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
    fun testDependencyOrdering() {
        val dep1 = Dependency("User", "User", "User.ts")
        val dep2 = Dependency("Role", "Role", "Role.ts")
        val dep3 = Dependency("User", "User", "User.ts")

        assertEquals(dep1, dep3)
        assertEquals(listOf(dep2, dep1), listOf(dep1, dep2).sorted())
    }
}
