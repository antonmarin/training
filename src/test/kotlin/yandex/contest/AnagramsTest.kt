package yandex.contest

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class AnagramsTest {
    @Test
    fun `should map chars by count`() {
        val input = "somestring"

        assertEquals(
            mapOf('s' to 2, 'o' to 1, 'm' to 1, 'e' to 1, 't' to 1, 'r' to 1, 'i' to 1, 'n' to 1, 'g' to 1),
            countAnagram(input)
        )
    }
}
