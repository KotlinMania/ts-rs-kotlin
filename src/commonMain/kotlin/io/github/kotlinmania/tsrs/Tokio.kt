// port-lint: source ts-rs/src/tokio.rs
package io.github.kotlinmania.tsrs

/**
 * TypeScript representation of Tokio synchronization primitives.
 */
public class TokioMutex<T : Ts>(
    public val inner: T,
) : Ts {
    override fun ident(): String = inner.ident()

    override fun name(): String = inner.name()

    override fun inline(): String = inner.inline()

    override fun inlineFlattened(): String = inner.inlineFlattened()

    override fun decl(): String = inner.decl()

    override fun declConcrete(): String = inner.declConcrete()

    override fun outputPath(): String? = inner.outputPath()

    override fun docs(): String? = inner.docs()

    override val isOption: Boolean get() = inner.isOption

    override fun visitDependencies(visitor: TypeVisitor) {
        visitor.visit(inner)
    }

    override fun visitGenerics(visitor: TypeVisitor) {
        inner.visitGenerics(visitor)
    }
}

public class TokioOnceCell<T : Ts>(
    public val inner: T,
) : Ts {
    override fun ident(): String = inner.ident()

    override fun name(): String = inner.name()

    override fun inline(): String = inner.inline()

    override fun inlineFlattened(): String = inner.inlineFlattened()

    override fun decl(): String = inner.decl()

    override fun declConcrete(): String = inner.declConcrete()

    override fun outputPath(): String? = inner.outputPath()

    override fun docs(): String? = inner.docs()

    override val isOption: Boolean get() = inner.isOption

    override fun visitDependencies(visitor: TypeVisitor) {
        visitor.visit(inner)
    }

    override fun visitGenerics(visitor: TypeVisitor) {
        inner.visitGenerics(visitor)
    }
}

public class TokioRwLock<T : Ts>(
    public val inner: T,
) : Ts {
    override fun ident(): String = inner.ident()

    override fun name(): String = inner.name()

    override fun inline(): String = inner.inline()

    override fun inlineFlattened(): String = inner.inlineFlattened()

    override fun decl(): String = inner.decl()

    override fun declConcrete(): String = inner.declConcrete()

    override fun outputPath(): String? = inner.outputPath()

    override fun docs(): String? = inner.docs()

    override val isOption: Boolean get() = inner.isOption

    override fun visitDependencies(visitor: TypeVisitor) {
        visitor.visit(inner)
    }

    override fun visitGenerics(visitor: TypeVisitor) {
        inner.visitGenerics(visitor)
    }
}
