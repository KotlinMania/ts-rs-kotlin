// port-lint: tests export.rs
package io.github.kotlinmania.tsrs.export

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

class ExportTest {
    @Test
    fun testImportExtension() {
        assertEquals("", importExtension(null).getOrThrow())
        assertEquals("", importExtension("").getOrThrow())
        assertEquals(".js", importExtension("js").getOrThrow())
        assertEquals(".ts", importExtension("ts").getOrThrow())

        val invalid = importExtension("json").exceptionOrNull()
        assertIs<ExportError.InvalidImportExtension>(invalid)
    }

    @Test
    fun testImportPath() {
        assertEquals(
            "./b",
            importPath("/workspace/bindings/a.ts", "/workspace/bindings/b.ts", currentDirectory = "/workspace").getOrThrow(),
        )
        assertEquals(
            "./b.js",
            importPath(
                "/workspace/bindings/a.ts",
                "/workspace/bindings/b.ts",
                importExtension = ".js",
                currentDirectory = "/workspace",
            ).getOrThrow(),
        )
        assertEquals(
            "../b",
            importPath("/workspace/bindings/sub/a.ts", "/workspace/bindings/b.ts", currentDirectory = "/workspace").getOrThrow(),
        )
    }

    @Test
    fun testMerge() {
        val original =
            "$NOTE" +
                "import type { ModLoader } from \"./b\";\n\n" +
                "export type User = { id: number };\n"

        val newContent =
            "$NOTE" +
                "import type { Hooks } from \"./b\";\n" +
                "import type { Extra } from \"./c\";\n\n" +
                "export type Admin = { user: User, role: string };\n"

        val merged = merge(original, newContent)

        assertTrue(merged.contains("import type { Hooks, ModLoader } from \"./b\";"))
        assertTrue(merged.contains("import type { Extra } from \"./c\";"))
        assertTrue(merged.contains("export type Admin = { user: User, role: string };"))
        assertTrue(merged.contains("export type User = { id: number };"))

        val adminIndex = merged.indexOf("export type Admin")
        val userIndex = merged.indexOf("export type User")
        assertTrue(adminIndex >= 0)
        assertTrue(userIndex >= 0)
        assertTrue(adminIndex < userIndex)
    }

    @Test
    fun testExportVisit() {
        val seen = mutableSetOf<String>()
        val visit = Visit(seen, "./bindings")
        assertEquals("./bindings", visit.outDir)
        assertEquals(0, visit.seen.size)
    }

    @Test
    fun testExportAndMerge() {
        val path = "/test/export/path.ts"
        val decl1 = "$NOTE\n\nexport type User = { id: number };\n"
        val decl2 = "$NOTE\n\nexport type Admin = { user: User, role: string };\n"

        exportAndMerge(path, "User", decl1).getOrThrow()
        assertEquals(decl1, getExportFiles()[path])
        assertEquals(setOf("User"), getExportPaths()[path])

        exportAndMerge(path, "Admin", decl2).getOrThrow()
        val merged = getExportFiles()[path]
        assertTrue(merged != null && merged.contains("export type Admin"))
        assertTrue(merged.contains("export type User"))
        assertEquals(setOf("User", "Admin"), getExportPaths()[path])
    }
}
