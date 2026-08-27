// port-lint: tests ts-rs/src/chrono.rs
package io.github.kotlinmania.tsrs

import kotlin.test.Test
import kotlin.test.assertEquals

class ChronoTest {
    @Test
    fun testChronoTypes() {
        assertEquals("string", ChronoTypes.NaiveDateTime.name())
        assertEquals("string", ChronoTypes.NaiveDate.name())
        assertEquals("string", ChronoTypes.NaiveTime.name())
        assertEquals("string", ChronoTypes.Month.name())
        assertEquals("string", ChronoTypes.Weekday.name())
        assertEquals("[number, number]", ChronoTypes.Duration.name())
        assertEquals("", ChronoTypes.Utc.name())
        assertEquals("", ChronoTypes.Local.name())
        assertEquals("", ChronoTypes.FixedOffset.name())

        val dt = ChronoTypes.DateTime(ChronoTypes.Utc)
        val d = ChronoTypes.Date(ChronoTypes.Utc)
        assertEquals("string", dt.name())
        assertEquals("string", d.name())
    }
}
