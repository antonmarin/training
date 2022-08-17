package contest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import yandex.contest.generateAll
import yandex.contest.printer
import yandex.contest.writeln
import java.util.stream.Stream

internal class GenerateBracesTest {
    @ParameterizedTest
    @MethodSource("provideBracesCases")
    fun `should generate`(input: Int, expected: List<String>) {
        assertEquals(expected, generateAll(input))
    }

    @Test
    fun `should generate simplest seq`() {
        assertEquals(
            "((()))",
            fillSeq(StringBuilder(), 3, 0, 0).toString()
        )
    }

    @Test
    fun profile() {
        assertFalse(generateAll(11).isEmpty())
    }

    @Test
    fun `should fill with valid sequence`() {
        val existedSeq = StringBuilder("()")
        assertEquals(
            "()()",
            fillSeq(existedSeq, 2, 1, 1).toString()
        )
    }


    companion object {
        @JvmStatic
        fun provideBracesCases(): Stream<Arguments> = Stream.of(
            Arguments.of(2, listOf("(())", "()()")),
            Arguments.of(3, listOf("((()))", "(()())", "(())()", "()(())", "()()()"))
        )
    }
}

fun main() {
    val expectedBracesCount = 3
    val sb = StringBuilder()
    var sequence = fillSeq(sb, expectedBracesCount)
    writeln(sequence)
    var reversibleOpenIndex = findLastReversibleOpenBraceIndex(sequence)
    while (reversibleOpenIndex != null) {
        sb.setCharAt(reversibleOpenIndex,')')
        sb.setLength(reversibleOpenIndex+1)
        sequence = fillSeq(sb, expectedBracesCount)
        writeln(sequence)
        reversibleOpenIndex = findLastReversibleOpenBraceIndex(sequence)
    }
    printer.close()
}

fun findLastReversibleOpenBraceIndex(seq: StringBuilder): Int? {
    var balanceClosed = 0
    for (i in (seq.length - 1) downTo 0) {
        val brace = seq[i]
        balanceClosed += if (brace == ')') -1 else 1
        if (balanceClosed < 0 && brace == '(') return i
    }
    return null
}
fun fillSeq(
    existedSeq: StringBuilder,
    expectedBracesCnt: Int,
    cntLeft: Int = existedSeq.count { it == '(' },
    cntRight: Int = existedSeq.count { it == ')' }
): StringBuilder {
    return when {
        cntLeft < expectedBracesCnt -> fillSeq(existedSeq.append('('), expectedBracesCnt, cntLeft + 1, cntRight)
        cntRight < cntLeft -> fillSeq(existedSeq.append(')'), expectedBracesCnt, cntLeft, cntRight + 1)
        else -> existedSeq // existedSeq.length == 2*expectedBracesCnt
    }
}
