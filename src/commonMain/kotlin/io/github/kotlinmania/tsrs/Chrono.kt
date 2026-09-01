// port-lint: source chrono.rs
package io.github.kotlinmania.tsrs

/**
 * TypeScript representation for chrono types.
 */
public object ChronoTypes {
    public object NaiveDateTime : Ts {
        override fun name(): String = "string"

        override fun inline(): String = "string"

        override fun decl(): String = error("NaiveDateTime cannot be declared")
    }

    public object NaiveDate : Ts {
        override fun name(): String = "string"

        override fun inline(): String = "string"

        override fun decl(): String = error("NaiveDate cannot be declared")
    }

    public object NaiveTime : Ts {
        override fun name(): String = "string"

        override fun inline(): String = "string"

        override fun decl(): String = error("NaiveTime cannot be declared")
    }

    public object Month : Ts {
        override fun name(): String = "string"

        override fun inline(): String = "string"

        override fun decl(): String = error("Month cannot be declared")
    }

    public object Weekday : Ts {
        override fun name(): String = "string"

        override fun inline(): String = "string"

        override fun decl(): String = error("Weekday cannot be declared")
    }

    public object Duration : Ts {
        override fun name(): String = "[number, number]"

        override fun inline(): String = "[number, number]"

        override fun decl(): String = error("Duration cannot be declared")
    }

    public object Utc : Ts {
        override fun name(): String = ""

        override fun inline(): String = ""

        override fun decl(): String = error("Utc cannot be declared")
    }

    public object Local : Ts {
        override fun name(): String = ""

        override fun inline(): String = ""

        override fun decl(): String = error("Local cannot be declared")
    }

    public object FixedOffset : Ts {
        override fun name(): String = ""

        override fun inline(): String = ""

        override fun decl(): String = error("FixedOffset cannot be declared")
    }

    public open class DateTime<T : Ts>(
        public val tz: T,
    ) : Ts {
        override fun ident(): String = "string"

        override fun name(): String = "string"

        override fun inline(): String = "string"

        override fun inlineFlattened(): String = error("DateTime cannot be flattened")

        override fun decl(): String = error("DateTime cannot be declared")

        override fun declConcrete(): String = error("DateTime cannot be declared")
    }

    public open class Date<T : Ts>(
        public val tz: T,
    ) : Ts {
        override fun ident(): String = "string"

        override fun name(): String = "string"

        override fun inline(): String = "string"

        override fun inlineFlattened(): String = error("Date cannot be flattened")

        override fun decl(): String = error("Date cannot be declared")

        override fun declConcrete(): String = error("Date cannot be declared")
    }
}
