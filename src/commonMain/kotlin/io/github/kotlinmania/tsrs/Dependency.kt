// port-lint: source ts-rs/src/lib.rs
package io.github.kotlinmania.tsrs

/**
 * A TypeScript type which is depended upon by other types.
 *
 * This information is required for generating the correct import statements.
 */
public data class Dependency(
    /** Identifier or type name of the source type */
    public val typeId: String,
    /** Name of the type in TypeScript */
    public val tsName: String,
    /** Path to where the type would be exported (relative to export dir) */
    public val outputPath: String,
) : Comparable<Dependency> {
    override fun compareTo(other: Dependency): Int {
        val byType = typeId.compareTo(other.typeId)
        if (byType != 0) return byType
        val byName = tsName.compareTo(other.tsName)
        if (byName != 0) return byName
        return outputPath.compareTo(other.outputPath)
    }

    public companion object {
        /**
         * Constructs a [Dependency] from the given type [type].
         * If [type] is not exportable, returns null.
         */
        public fun fromTy(type: Ts): Dependency? {
            val out = type.outputPath() ?: return null
            return Dependency(
                typeId = type.ident(),
                tsName = type.ident(),
                outputPath = out,
            )
        }
    }
}
