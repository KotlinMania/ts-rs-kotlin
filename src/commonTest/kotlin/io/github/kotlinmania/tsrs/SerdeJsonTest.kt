// port-lint: tests ts-rs/src/serde_json.rs
package io.github.kotlinmania.tsrs

import kotlin.test.Test
import kotlin.test.assertEquals

class SerdeJsonTest {
    @Test
    fun testTsJsonValueVariants() {
        val num = TsJsonValue.Number(42)
        val str = TsJsonValue.StringValue("hello")
        val bool = TsJsonValue.BooleanValue(true)
        val arr = TsJsonValue.ArrayValue(listOf(num, str))
        val obj = TsJsonValue.ObjectValue(mapOf("key" to bool))
        val nil = TsJsonValue.NullValue

        assertEquals("number", num.name())
        assertEquals("string", str.name())
        assertEquals("boolean", bool.name())
        assertEquals("Array<JsonValue>", arr.name())
        assertEquals("{ [key: string]: JsonValue }", obj.name())
        assertEquals("null", nil.name())

        assertEquals("JsonValue", TsJsonValue.ident())
        assertEquals("serde_json/JsonValue.ts", TsJsonValue.outputPath())
        assertEquals("number", SerdeJsonNumber.name())
    }

    @Test
    fun testSerdeJsonMap() {
        val keyType =
            object : Ts {
                override fun name(): String = "string"

                override fun inline(): String = "string"

                override fun decl(): String = ""
            }
        val valType =
            object : Ts {
                override fun name(): String = "number"

                override fun inline(): String = "number"

                override fun decl(): String = ""
            }
        val map = SerdeJsonMap(keyType, valType)
        assertEquals("{ [key: string]: number }", map.name())
        assertEquals("{ [key: string]: number }", map.inline())
    }
}
