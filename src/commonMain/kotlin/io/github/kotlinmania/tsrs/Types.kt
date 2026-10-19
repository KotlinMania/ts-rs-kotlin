package io.github.kotlinmania.tsrs

/**
 * TypeScript representation of Option<T>.
 */
public class TsOption<T : Ts>(
    public val inner: T,
) : Ts,
    IsOption {
    override val isOption: Boolean get() = true

    override fun name(): String = "${inner.name()} | null"

    override fun inline(): String = "${inner.inline()} | null"

    override fun visitDependencies(visitor: TypeVisitor) {
        inner.visitDependencies(visitor)
    }

    override fun visitGenerics(visitor: TypeVisitor) {
        inner.visitGenerics(visitor)
        visitor.visit(inner)
    }

    override fun decl(): String = error("${name()} cannot be declared")

    override fun declConcrete(): String = error("${name()} cannot be declared")

    override fun inlineFlattened(): String = error("${name()} cannot be flattened")
}

/**
 * TypeScript representation of Result<T, E>.
 */
public class TsResult<T : Ts, E : Ts>(
    public val okType: T,
    public val errType: E,
) : Ts {
    override fun name(): String = "{ Ok : ${okType.name()} } | { Err : ${errType.name()} }"

    override fun inline(): String = "{ Ok : ${okType.inline()} } | { Err : ${errType.inline()} }"

    override fun visitDependencies(visitor: TypeVisitor) {
        okType.visitDependencies(visitor)
        errType.visitDependencies(visitor)
    }

    override fun visitGenerics(visitor: TypeVisitor) {
        okType.visitGenerics(visitor)
        visitor.visit(okType)
        errType.visitGenerics(visitor)
        visitor.visit(errType)
    }

    override fun decl(): String = error("${name()} cannot be declared")

    override fun declConcrete(): String = error("${name()} cannot be declared")

    override fun inlineFlattened(): String = error("${name()} cannot be flattened")
}

/**
 * TypeScript representation of Vec<T> / arrays.
 */
public class TsArray<T : Ts>(
    public val itemType: T,
) : Ts {
    override fun ident(): String = "Array"

    override fun name(): String = "Array<${itemType.name()}>"

    override fun inline(): String = "Array<${itemType.inline()}>"

    override fun visitDependencies(visitor: TypeVisitor) {
        itemType.visitDependencies(visitor)
    }

    override fun visitGenerics(visitor: TypeVisitor) {
        itemType.visitGenerics(visitor)
        visitor.visit(itemType)
    }

    override fun decl(): String = error("${name()} cannot be declared")

    override fun declConcrete(): String = error("${name()} cannot be declared")

    override fun inlineFlattened(): String = error("${name()} cannot be flattened")
}

/**
 * TypeScript representation of HashMap<K, V>.
 */
public class TsMap<K : Ts, V : Ts>(
    public val keyType: K,
    public val valueType: V,
) : Ts {
    override fun ident(): String = error("ident cannot be called on TsMap")

    override fun name(): String = "{ [key in ${keyType.name()}]?: ${valueType.name()} }"

    override fun inline(): String = "{ [key in ${keyType.inline()}]?: ${valueType.inline()} }"

    override fun visitDependencies(visitor: TypeVisitor) {
        keyType.visitDependencies(visitor)
        valueType.visitDependencies(visitor)
    }

    override fun visitGenerics(visitor: TypeVisitor) {
        keyType.visitGenerics(visitor)
        visitor.visit(keyType)
        valueType.visitGenerics(visitor)
        visitor.visit(valueType)
    }

    override fun decl(): String = error("${name()} cannot be declared")

    override fun declConcrete(): String = error("${name()} cannot be declared")

    override fun inlineFlattened(): String = "({ [key in ${keyType.inline()}]?: ${valueType.inline()} })"
}

/**
 * TypeScript representation of Range<I>.
 */
public class TsRange<I : Ts>(
    public val itemType: I,
) : Ts {
    override fun name(): String = "{ start: ${itemType.name()}, end: ${itemType.name()}, }"

    override fun inline(): String = error("${name()} cannot be inlined")

    override fun visitDependencies(visitor: TypeVisitor) {
        itemType.visitDependencies(visitor)
    }

    override fun visitGenerics(visitor: TypeVisitor) {
        itemType.visitGenerics(visitor)
        visitor.visit(itemType)
    }

    override fun decl(): String = error("${name()} cannot be declared")

    override fun declConcrete(): String = error("${name()} cannot be declared")

    override fun inlineFlattened(): String = error("${name()} cannot be flattened")
}

/**
 * TypeScript representation of primitive types.
 */
public open class TsPrimitive(
    private val typeName: String,
) : Ts,
    WithoutGenerics,
    OptionInnerType {
    override fun ident(): String = typeName

    override fun name(): String = typeName

    override fun inline(): String = typeName

    override fun decl(): String = error("$typeName cannot be declared")

    override fun declConcrete(): String = error("$typeName cannot be declared")

    override fun inlineFlattened(): String = error("$typeName cannot be flattened")
}

public object TsNumber : TsPrimitive("number")

public object TsBigInt : TsPrimitive("bigint")

public object TsBoolean : TsPrimitive("boolean")

public object TsString : TsPrimitive("string")

public object TsNull : TsPrimitive("null")

/**
 * TypeScript representation of indexmap::IndexSet<T>.
 */
public class TsIndexSet<T : Ts>(
    public val itemType: T,
) : Ts {
    private val delegate = TsArray(itemType)

    override fun ident(): String = delegate.ident()

    override fun name(): String = delegate.name()

    override fun inline(): String = delegate.inline()

    override fun visitDependencies(visitor: TypeVisitor) {
        delegate.visitDependencies(visitor)
    }

    override fun visitGenerics(visitor: TypeVisitor) {
        delegate.visitGenerics(visitor)
    }

    override fun decl(): String = delegate.decl()

    override fun declConcrete(): String = delegate.declConcrete()

    override fun inlineFlattened(): String = delegate.inlineFlattened()
}

/**
 * TypeScript representation of indexmap::IndexMap<K, V>.
 */
public class TsIndexMap<K : Ts, V : Ts>(
    public val keyType: K,
    public val valueType: V,
) : Ts {
    private val delegate = TsMap(keyType, valueType)

    override fun name(): String = delegate.name()

    override fun inline(): String = delegate.inline()

    override fun visitDependencies(visitor: TypeVisitor) {
        delegate.visitDependencies(visitor)
    }

    override fun visitGenerics(visitor: TypeVisitor) {
        delegate.visitGenerics(visitor)
    }

    override fun decl(): String = delegate.decl()

    override fun declConcrete(): String = delegate.declConcrete()

    override fun inlineFlattened(): String = delegate.inlineFlattened()
}

/**
 * TypeScript representation of bytes::Bytes.
 */
public object TsBytes : Ts {
    private val delegate = TsArray(TsNumber)

    override fun ident(): String = delegate.ident()

    override fun name(): String = delegate.name()

    override fun inline(): String = delegate.inline()

    override fun decl(): String = delegate.decl()

    override fun declConcrete(): String = delegate.declConcrete()

    override fun inlineFlattened(): String = delegate.inlineFlattened()
}

/**
 * TypeScript representation of uuid::Uuid.
 */
public object TsUuid : TsPrimitive("string")

/**
 * TypeScript representation of semver::Version.
 */
public object TsSemver : TsPrimitive("string")
