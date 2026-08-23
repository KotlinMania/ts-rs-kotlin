// port-lint: source src/lib.rs
package io.github.kotlinmania.tsrs

/**
 * A type which can be represented in TypeScript.
 */
public interface Ts {
    /** JSDoc comment to describe this type in TypeScript */
    public fun docs(): String? = null

    /** Identifier of this type, excluding generic parameters */
    public fun ident(): String {
        val n = name()
        val idx = n.indexOf('<')
        return if (idx >= 0) n.substring(0, idx) else n
    }

    /** Declaration of this type, e.g. `type User = { user_id: number, ... }` */
    public fun decl(): String

    /** Declaration of this type using supplied generic arguments */
    public fun declConcrete(): String = decl()

    /** Name of this type in TypeScript, including generic parameters */
    public fun name(): String

    /** Formats this type's definition in TypeScript, e.g. `{ user_id: number }` */
    public fun inline(): String

    /** Flatten a type declaration */
    public fun inlineFlattened(): String = error("${name()} cannot be flattened")

    /** Returns the output path to where the type should be exported */
    public fun outputPath(): String? = null

    /** Resolves all dependencies of this type */
    public fun dependencies(): List<Dependency> = emptyList()

    /** Whether this type represents an optional / nullable type */
    public val isOption: Boolean get() = false
}
