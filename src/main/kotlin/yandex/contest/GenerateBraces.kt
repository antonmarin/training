package yandex.contest

import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() {
    val input = 4
    generateAll(input).forEach { println(it) }
}

val printer = BufferedWriter(OutputStreamWriter(System.out))
fun writeln(str:StringBuilder) {
    printer.write(str.toString())
    printer.newLine()
}

fun generateAll(expectedBracesCount: Int): List<CharSequence> {
    val result = mutableListOf<CharSequence>()
    val sb = StringBuilder()
    var sequence = fillSeq(sb, expectedBracesCount)
    result.add(sequence.toString())
    var reversibleOpenIndex = findLastReversibleOpenBraceIndex(sequence)
    while (reversibleOpenIndex != null) {
        sb.setCharAt(reversibleOpenIndex,')')
        sb.setLength(reversibleOpenIndex+1)
        sequence = fillSeq(sb, expectedBracesCount)
        result.add(sequence.toString())
        reversibleOpenIndex = findLastReversibleOpenBraceIndex(sequence)
    }

    return result
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
