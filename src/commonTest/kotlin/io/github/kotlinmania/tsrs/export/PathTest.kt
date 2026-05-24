// port-lint: source src/export/path.rs
package io.github.kotlinmania.tsrs.export

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class PathTest {
    @Test
    fun absoluteCleansRelativePathsAgainstCurrentDirectory() {
        assertEquals(
            "/workspace/bindings/export_to/File.ts",
            absolute("bindings/./path_bug/../export_to/File.ts", "/workspace").getOrThrow(),
        )
    }

    @Test
    fun absoluteRejectsInvalidParentTraversal() {
        val failure = absolute("../../bindings", ".").exceptionOrNull()

        assertIs<ExportError.CannotBeExported>(failure)
    }

    @Test
    fun diffPathsBuildsRelativePathFromBaseDirectory() {
        assertEquals(
            "../shared/User.ts",
            diffPaths("/workspace/bindings/shared/User.ts", "/workspace/bindings/export").getOrThrow(),
        )
    }

    @Test
    fun diffPathsKeepsDescendantPathsRelative() {
        assertEquals(
            "nested/User.ts",
            diffPaths("/workspace/bindings/export/nested/User.ts", "/workspace/bindings/export").getOrThrow(),
        )
    }
}
