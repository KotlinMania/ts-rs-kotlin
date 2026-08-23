// port-lint: source lib.rs
package io.github.kotlinmania.tsrs

import io.github.kotlinmania.tsrs.export.ExportError

/**
 * A visitor used to iterate over all dependencies or generics of a type.
 */
public fun interface TypeVisitor {
    public fun visit(type: Ts)
}

/**
 * Marker interface for types without generic parameters.
 */
public interface WithoutGenerics : Ts

/**
 * Marker interface for option inner types.
 */
public interface OptionInnerType

/**
 * Dummy placeholder type for generics resolution.
 */
public object Dummy : Ts {
    override fun name(): String = ""

    override fun inline(): String = ""

    override fun decl(): String = error("Dummy cannot be declared")

    override fun declConcrete(): String = error("Dummy cannot be declared")

    override fun inlineFlattened(): String = error("Dummy cannot be flattened")
}

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

    /** Declaration of this type, e.g. `type User = { userId: number, ... }` */
    public fun decl(): String

    /** Declaration of this type using supplied generic arguments */
    public fun declConcrete(): String = decl()

    /** Name of this type in TypeScript, including generic parameters */
    public fun name(): String

    /** Formats this type definition in TypeScript, e.g. `{ userId: number }` */
    public fun inline(): String

    /** Flatten a type declaration */
    public fun inlineFlattened(): String = error("${name()} cannot be flattened")

    /** Returns the output path to where the type should be exported */
    public fun outputPath(): String? = null

    /** Returns the default output path including default directory */
    public fun defaultOutputPath(baseDir: String = "./bindings"): String? {
        val path = outputPath() ?: return null
        return "${baseDir.trimEnd('/')}/$path"
    }

    /** Iterates over all dependencies of this type */
    public fun visitDependencies(visitor: TypeVisitor) {
    }

    /** Iterates over all type parameters of this type */
    public fun visitGenerics(visitor: TypeVisitor) {
    }

    /** Resolves all dependencies of this type */
    public fun dependencies(): List<Dependency> {
        val deps = mutableListOf<Dependency>()
        visitDependencies { depType ->
            Dependency.fromTy(depType)?.let { deps.add(it) }
        }
        return deps
    }

    /** Manually export this type definition to string */
    public fun exportToString(): Result<String> =
        io.github.kotlinmania.tsrs.export
            .exportToString(this)

    /** Manually export this type to default path */
    public fun export(): Result<Unit> {
        val path =
            defaultOutputPath() ?: return Result.failure(
                ExportError.CannotBeExported(name()),
            )
        return io.github.kotlinmania.tsrs.export
            .exportTo(this, path)
    }

    /** Manually export this type and all dependencies */
    public fun exportAll(baseDir: String = "./bindings"): Result<Unit> =
        io.github.kotlinmania.tsrs.export
            .exportAllInto(this, baseDir)

    /** Manually export this type and all dependencies to given directory */
    public fun exportAllTo(outDir: String): Result<Unit> =
        io.github.kotlinmania.tsrs.export
            .exportAllInto(this, outDir)

    /** Whether this type represents an optional / nullable type */
    public val isOption: Boolean get() = false

    /** Formats doc comment into JSDoc style */
    public fun formatDocs(): String? {
        val d = docs() ?: return null
        return "/**\n * " + d.lines().joinToString("\n * ") + "\n */\n"
    }
}
