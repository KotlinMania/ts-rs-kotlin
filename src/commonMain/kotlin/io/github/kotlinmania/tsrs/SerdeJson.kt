// port-lint: source ts-rs/src/serde_json.rs
package io.github.kotlinmania.tsrs

/**
 * TypeScript representation of Serde JSON types.
 */
public sealed class TsJsonValue : Ts {
    public data class Number(
        public val value: Int,
    ) : TsJsonValue() {
        override fun name(): String = "number"

        override fun inline(): String = "number"

        override fun decl(): String = "type JsonValue = number | string | boolean | Array<JsonValue> | { [key: string]: JsonValue } | null;"
    }

    public data class StringValue(
        public val value: String,
    ) : TsJsonValue() {
        override fun name(): String = "string"

        override fun inline(): String = "string"

        override fun decl(): String = "type JsonValue = number | string | boolean | Array<JsonValue> | { [key: string]: JsonValue } | null;"
    }

    public data class BooleanValue(
        public val value: Boolean,
    ) : TsJsonValue() {
        override fun name(): String = "boolean"

        override fun inline(): String = "boolean"

        override fun decl(): String = "type JsonValue = number | string | boolean | Array<JsonValue> | { [key: string]: JsonValue } | null;"
    }

    public data class ArrayValue(
        public val items: List<TsJsonValue>,
    ) : TsJsonValue() {
        override fun name(): String = "Array<JsonValue>"

        override fun inline(): String = "Array<JsonValue>"

        override fun decl(): String = "type JsonValue = number | string | boolean | Array<JsonValue> | { [key: string]: JsonValue } | null;"
    }

    public data class ObjectValue(
        public val entries: Map<String, TsJsonValue>,
    ) : TsJsonValue() {
        override fun name(): String = "{ [key: string]: JsonValue }"

        override fun inline(): String = "{ [key: string]: JsonValue }"

        override fun decl(): String = "type JsonValue = number | string | boolean | Array<JsonValue> | { [key: string]: JsonValue } | null;"
    }

    public object NullValue : TsJsonValue() {
        override fun name(): String = "null"

        override fun inline(): String = "null"

        override fun decl(): String = "type JsonValue = number | string | boolean | Array<JsonValue> | { [key: string]: JsonValue } | null;"
    }

    override fun ident(): String = "JsonValue"

    override fun name(): String = "JsonValue"

    override fun inline(): String = "number | string | boolean | Array<JsonValue> | { [key: string]: JsonValue } | null"

    override fun decl(): String = "type JsonValue = number | string | boolean | Array<JsonValue> | { [key: string]: JsonValue } | null;"

    override fun outputPath(): String = "serde_json/JsonValue.ts"

    public companion object : Ts {
        override fun ident(): String = "JsonValue"

        override fun name(): String = "JsonValue"

        override fun inline(): String = "number | string | boolean | Array<JsonValue> | { [key: string]: JsonValue } | null"

        override fun decl(): String = "type JsonValue = number | string | boolean | Array<JsonValue> | { [key: string]: JsonValue } | null;"

        override fun outputPath(): String = "serde_json/JsonValue.ts"
    }
}

public object SerdeJsonNumber : Ts {
    override fun ident(): String = "number"

    override fun name(): String = "number"

    override fun inline(): String = "number"

    override fun decl(): String = error("Number cannot be declared")
}

public class SerdeJsonMap<K : Ts, V : Ts>(
    public val keyType: K,
    public val valueType: V,
) : Ts {
    override fun ident(): String = "{ [key: string]: ${valueType.name()} }"

    override fun name(): String = "{ [key: string]: ${valueType.name()} }"

    override fun inline(): String = "{ [key: string]: ${valueType.inline()} }"

    override fun decl(): String = error("Map cannot be declared")

    override fun visitDependencies(visitor: TypeVisitor) {
        visitor.visit(keyType)
        visitor.visit(valueType)
    }
}
