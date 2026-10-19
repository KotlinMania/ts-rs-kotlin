package io.github.kotlinmania.tsrs

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class TypesTest {
    @Test
    fun testPrimitives() {
        assertEquals("number", TsNumber.ident())
        assertEquals("number", TsNumber.name())
        assertEquals("number", TsNumber.inline())

        assertEquals("bigint", TsBigInt.ident())
        assertEquals("bigint", TsBigInt.name())
        assertEquals("bigint", TsBigInt.inline())

        assertEquals("boolean", TsBoolean.ident())
        assertEquals("boolean", TsBoolean.name())
        assertEquals("boolean", TsBoolean.inline())

        assertEquals("string", TsString.ident())
        assertEquals("string", TsString.name())
        assertEquals("string", TsString.inline())

        assertEquals("null", TsNull.ident())
        assertEquals("null", TsNull.name())
        assertEquals("null", TsNull.inline())

        assertFailsWith<IllegalStateException> { TsNumber.decl() }
        assertFailsWith<IllegalStateException> { TsNumber.declConcrete() }
        assertFailsWith<IllegalStateException> { TsNumber.inlineFlattened() }
    }

    @Test
    fun testOption() {
        val opt = TsOption(TsNumber)
        assertTrue(opt.isOption)
        assertEquals("number | null", opt.name())
        assertEquals("number | null", opt.inline())
        assertFailsWith<IllegalStateException> { opt.decl() }
        assertFailsWith<IllegalStateException> { opt.declConcrete() }
        assertFailsWith<IllegalStateException> { opt.inlineFlattened() }

        val deps = mutableListOf<Dependency>()
        opt.visitDependencies(Visit(deps))
        assertEquals(0, deps.size)
    }

    @Test
    fun testResult() {
        val res = TsResult(TsString, TsNumber)
        assertEquals("{ Ok : string } | { Err : number }", res.name())
        assertEquals("{ Ok : string } | { Err : number }", res.inline())
        assertFailsWith<IllegalStateException> { res.decl() }
        assertFailsWith<IllegalStateException> { res.declConcrete() }
        assertFailsWith<IllegalStateException> { res.inlineFlattened() }
    }

    @Test
    fun testArray() {
        val arr = TsArray(TsString)
        assertEquals("Array", arr.ident())
        assertEquals("Array<string>", arr.name())
        assertEquals("Array<string>", arr.inline())
        assertFailsWith<IllegalStateException> { arr.decl() }
        assertFailsWith<IllegalStateException> { arr.declConcrete() }
        assertFailsWith<IllegalStateException> { arr.inlineFlattened() }
    }

    @Test
    fun testMap() {
        val map = TsMap(TsString, TsNumber)
        assertEquals("{ [key in string]?: number }", map.name())
        assertEquals("{ [key in string]?: number }", map.inline())
        assertEquals("({ [key in string]?: number })", map.inlineFlattened())
        assertFailsWith<IllegalStateException> { map.ident() }
        assertFailsWith<IllegalStateException> { map.decl() }
        assertFailsWith<IllegalStateException> { map.declConcrete() }
    }

    @Test
    fun testRange() {
        val range = TsRange(TsNumber)
        assertEquals("{ start: number, end: number, }", range.name())
        assertFailsWith<IllegalStateException> { range.inline() }
        assertFailsWith<IllegalStateException> { range.decl() }
        assertFailsWith<IllegalStateException> { range.declConcrete() }
        assertFailsWith<IllegalStateException> { range.inlineFlattened() }
    }

    @Test
    fun testSiblingBindings() {
        val set = TsIndexSet(TsString)
        assertEquals("Array", set.ident())
        assertEquals("Array<string>", set.name())
        assertEquals("Array<string>", set.inline())

        val indexMap = TsIndexMap(TsString, TsNumber)
        assertEquals("{ [key in string]?: number }", indexMap.name())
        assertEquals("{ [key in string]?: number }", indexMap.inline())

        assertEquals("Array", TsBytes.ident())
        assertEquals("Array<number>", TsBytes.name())
        assertEquals("Array<number>", TsBytes.inline())

        assertEquals("string", TsUuid.ident())
        assertEquals("string", TsUuid.name())
        assertEquals("string", TsUuid.inline())

        assertEquals("string", TsSemver.ident())
        assertEquals("string", TsSemver.name())
        assertEquals("string", TsSemver.inline())
    }
}
