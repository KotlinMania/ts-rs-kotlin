package io.github.kotlinmania.tsrs

import io.github.kotlinmania.tsrs.export.ExportError

/**
 * A visitor used to iterate over all dependencies or generics of a type.
 * When an instance of [TypeVisitor] is passed to [Ts.visitDependencies] or
 * [Ts.visitGenerics], the [TypeVisitor.visit] method will be invoked for every dependency
 * or generic parameter respectively.
 */
public interface TypeVisitor {
    public fun visit(type: Ts)
}

/**
 * Dependency accumulator implementing [TypeVisitor].
 */
public class Visit internal constructor(
    internal val deps: MutableList<Dependency>,
) : TypeVisitor {
    override fun visit(type: Ts) {
        Dependency.fromTy(type)?.let { deps.add(it) }
    }
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
 * Marker interface for types that represent an Option.
 */
public interface IsOption

/**
 * Dummy fallback type for generics resolution.
 */
public object Dummy : Ts {
    public fun fmt(): String = "Dummy"

    override fun toString(): String = fmt()

    override fun name(): String = "Dummy"

    override fun inline(): String = ""

    override fun decl(): String = error("${name()} cannot be declared")

    override fun declConcrete(): String = error("${name()} cannot be declared")

    override fun inlineFlattened(): String = error("${name()} cannot be flattened")
}

/**
 * A type which can be represented in TypeScript.
 */
public interface Ts {
    /**
     * JSDoc comment to describe this type in TypeScript.
     * When Ts is derived, docs are automatically read from doc comments.
     */
    public fun docs(): String? = null

    /**
     * Identifier of this type, excluding generic parameters.
     */
    public fun ident(): String {
        val n = name()
        val idx = n.indexOf('<')
        return if (idx >= 0) n.substring(0, idx) else n
    }

    /**
     * Declaration of this type, e.g. `type User = { userId: number, ... }`.
     * This function will throw if the type has no declaration.
     *
     * If this type is generic, then all provided generic parameters will be swapped for
     * placeholders, resulting in a generic typescript definition.
     */
    public fun decl(): String

    /**
     * Declaration of this type using the supplied generic arguments.
     * The resulting TypeScript definition will not be generic. For that, see [decl].
     * If this type is not generic, then this function is equivalent to [decl].
     */
    public fun declConcrete(): String = decl()

    /**
     * Name of this type in TypeScript, including generic parameters.
     */
    public fun name(): String

    /**
     * Formats this type definition in TypeScript, e.g `{ userId: number }`.
     * This function will throw if the type cannot be inlined.
     */
    public fun inline(): String

    /**
     * Flatten a type declaration.
     * This function will throw if the type cannot be flattened.
     */
    public fun inlineFlattened(): String = error("${name()} cannot be flattened")

    /**
     * Returns the output path to where the type should be exported.
     * The returned path does not include the base directory.
     * To get the output path containing the base directory, use [defaultOutputPath].
     * If the type cannot be exported, this function will return null.
     */
    public fun outputPath(): String? = null

    /**
     * Returns the output path to where the type should be exported, including default directory.
     * If the type cannot be exported, this function will return null.
     */
    public fun defaultOutputPath(baseDir: String = "./bindings"): String? {
        val path = outputPath() ?: return null
        return "${baseDir.trimEnd('/')}/$path"
    }

    /**
     * Iterates over all dependencies of this type.
     */
    public fun visitDependencies(visitor: TypeVisitor) {
        visitor.run {}
    }

    /**
     * Iterates over all type parameters of this type.
     */
    public fun visitGenerics(visitor: TypeVisitor) {
        visitor.run {}
    }

    /**
     * Resolves all dependencies of this type recursively.
     */
    public fun dependencies(): List<Dependency> {
        val deps = mutableListOf<Dependency>()
        val visitor = Visit(deps)
        visitDependencies(visitor)
        return deps
    }

    /**
     * Manually generate bindings for this type, returning a String.
     */
    public fun exportToString(): Result<String> =
        io.github.kotlinmania.tsrs.export
            .exportToString(this)

    /**
     * Manually export this type to the filesystem.
     * To export this type together with all of its dependencies, use [exportAll].
     */
    public fun export(): Result<Unit> {
        val path =
            defaultOutputPath() ?: return Result.failure(
                ExportError.CannotBeExported(name()),
            )
        return io.github.kotlinmania.tsrs.export
            .exportTo(this, path)
    }

    /**
     * Manually export this type to the filesystem, together with all of its dependencies.
     * To export only this type, without its dependencies, use [export].
     */
    public fun exportAll(baseDir: String = "./bindings"): Result<Unit> =
        io.github.kotlinmania.tsrs.export
            .exportAllInto(this, baseDir)

    /**
     * Manually export this type into the given directory, together with all of its dependencies.
     * To export only this type, without its dependencies, use [export].
     */
    public fun exportAllTo(outDir: String): Result<Unit> =
        io.github.kotlinmania.tsrs.export
            .exportAllInto(this, outDir)

    /**
     * Whether this type represents an optional / nullable type.
     */
    public val isOption: Boolean get() = false

    /**
     * Formats doc comment into JSDoc style.
     */
    public fun formatDocs(): String? {
        val d = docs() ?: return null
        return "/**\n * " + d.lines().joinToString("\n * ") + "\n */\n"
    }
}
